package com.example.adsnippet.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.adsnippet.R
import com.example.adsnippet.components.AppBar
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdBannerScreen(onNextClick: () -> Unit) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = { AppBar(name = stringResource(R.string.label_admob_banner)) },
        bottomBar = {
            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = { context ->
                    AdView(context).apply {
                        setAdSize(AdSize.FULL_BANNER)
                        adUnitId = "ca-app-pub-3940256099942544/9214589741" // Replace with your ad unit ID
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
        }
    ) { paddingValues ->  
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(stringResource(R.string.admob_banner))

            Spacer(Modifier.height(6.dp))

            Text(stringResource(R.string.admob_interstitial_hint))

            Button(onClick = onNextClick) {
                Text(stringResource(R.string.button_next))
            }
        }
    }
}