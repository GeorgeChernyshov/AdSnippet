package com.example.adsnippet.screen

import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.adsnippet.R
import com.example.adsnippet.components.AdNativeView
import com.example.adsnippet.components.AppBar
import com.example.adsnippet.databinding.ViewNativeAdBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions

@Composable
fun AdNativeScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        topBar = { AppBar(name = stringResource(R.string.label_admob_native)) }
    ) { paddingValues ->
        LazyColumn(Modifier.padding(paddingValues)) {
            item {
                Text(stringResource(R.string.admob_native_small))
            }

            item {
                AndroidView(factory = { context ->
                    val adView = AdNativeView(context)
                    val adLoader = AdLoader
                        .Builder(
                            context,
                            "ca-app-pub-3940256099942544/2247696110"
                        )
                        .forNativeAd { ad : NativeAd ->
                            populateNativeAdView(ad, adView.binding)
                        }
                        .withAdListener(object : AdListener() {
                            override fun onAdFailedToLoad(adError: LoadAdError) {
                                // Handle the failure.
                            }
                        })
                        .withNativeAdOptions(NativeAdOptions.Builder().build())
                        .build()

                    adLoader.loadAd(AdRequest.Builder().build())

                    return@AndroidView adView
                })
            }
        }
    }
}

private fun populateNativeAdView(
    nativeAd: NativeAd,
    adBinding: ViewNativeAdBinding
) {
    val nativeAdView = adBinding.root

    // Set the media view.
    nativeAdView.mediaView = adBinding.adMedia

    nativeAdView.headlineView = adBinding.adHeadline
    nativeAdView.bodyView = adBinding.adBody
    nativeAdView.callToActionView = adBinding.adCallToAction
    nativeAdView.priceView = adBinding.adPrice
    nativeAdView.starRatingView = adBinding.adStars
    nativeAdView.storeView = adBinding.adStore
    nativeAdView.advertiserView = adBinding.adAdvertiser

    // The headline and media content are guaranteed to be in every UnifiedNativeAd.
    adBinding.adHeadline.text = nativeAd.headline
    nativeAd.mediaContent?.let {
        adBinding.adMedia.mediaContent = it
    }

    // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
    // check before trying to display them.
    if (nativeAd.body == null) {
        adBinding.adBody.visibility = View.INVISIBLE
    } else {
        adBinding.adBody.visibility = View.VISIBLE
        adBinding.adBody.text = nativeAd.body
    }

    if (nativeAd.callToAction == null) {
        adBinding.adCallToAction.visibility = View.INVISIBLE
    } else {
        adBinding.adCallToAction.visibility = View.VISIBLE
        adBinding.adCallToAction.text = nativeAd.callToAction
    }

    if (nativeAd.icon == null) {
        adBinding.adAppIcon.visibility = View.GONE
    } else {
        adBinding.adAppIcon.setImageDrawable(nativeAd.icon?.drawable)
        adBinding.adAppIcon.visibility = View.VISIBLE
    }

    if (nativeAd.price == null) {
        adBinding.adPrice.visibility = View.INVISIBLE
    } else {
        adBinding.adPrice.visibility = View.VISIBLE
        adBinding.adPrice.text = nativeAd.price
    }

    if (nativeAd.store == null) {
        adBinding.adStore.visibility = View.INVISIBLE
    } else {
        adBinding.adStore.visibility = View.VISIBLE
        adBinding.adStore.text = nativeAd.store
    }

    if (nativeAd.starRating == null) {
        adBinding.adStars.visibility = View.INVISIBLE
    } else {
        adBinding.adStars.rating = nativeAd.starRating!!.toFloat()
        adBinding.adStars.visibility = View.VISIBLE
    }

    if (nativeAd.advertiser == null) {
        adBinding.adAdvertiser.visibility = View.INVISIBLE
    } else {
        adBinding.adAdvertiser.text = nativeAd.advertiser
        adBinding.adAdvertiser.visibility = View.VISIBLE
    }

    // This method tells the Google Mobile Ads SDK that you have finished populating your
    // native ad view with this native ad.
    nativeAdView.setNativeAd(nativeAd)
}