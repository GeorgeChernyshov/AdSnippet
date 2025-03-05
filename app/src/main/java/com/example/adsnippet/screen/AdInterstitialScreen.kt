package com.example.adsnippet.screen

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.adsnippet.R
import com.example.adsnippet.components.AppBar
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

@Composable
fun AdInterstitialScreen(
    mainActivity: Activity
) {
    val context = LocalContext.current

    var interstitialAd by remember { mutableStateOf<InterstitialAd?>(null) }
    var adLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(interstitialAd) {
        interstitialAd?.let {
            showInterstitial(
                interstitialAd = it,
                activity = mainActivity,
                onFailure = { interstitialAd = null }
            )

            return@LaunchedEffect
        }

        if (adLoaded) return@LaunchedEffect

        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            context,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    interstitialAd = null
                }

                override fun onAdLoaded(p0: InterstitialAd) {
                    adLoaded = true
                    interstitialAd = p0
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AppBar(name = stringResource(R.string.label_admob_interstitial))
        Text(stringResource(R.string.admob_interstitial_hint_after))
    }
}

private fun showInterstitial(
    interstitialAd: InterstitialAd,
    activity: Activity,
    onFailure: () -> Unit
) {
    interstitialAd.fullScreenContentCallback = object : FullScreenContentCallback() {
        override fun onAdDismissedFullScreenContent() {
            Log.d(TAG, "Ad was dismissed.")
            onFailure()
        }

        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
            Log.d(TAG, "Ad failed to show.")
            onFailure()
        }

        override fun onAdShowedFullScreenContent() {
            Log.d(TAG, "Ad showed fullscreen content.")
        }
    }

    interstitialAd.show(activity)
}

private const val TAG = "Interstitial Ad"