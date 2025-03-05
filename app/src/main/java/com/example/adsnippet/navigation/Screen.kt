package com.example.adsnippet.navigation

import androidx.annotation.StringRes
import com.example.adsnippet.R
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String, @StringRes val resourceId: Int) {

    @Serializable
    data object AdMobBanner : Screen(
        route = "adMobBanner",
        resourceId = R.string.label_admob_banner
    )

    @Serializable
    data object AdMobInterstitial : Screen(
        route = "adMobInterstitial",
        resourceId = R.string.label_admob_interstitial
    )
}