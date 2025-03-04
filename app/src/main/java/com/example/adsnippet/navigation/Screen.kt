package com.example.adsnippet.navigation

import androidx.annotation.StringRes
import com.example.adsnippet.R
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String, @StringRes val resourceId: Int) {

    @Serializable
    data object AdMob : Screen(
        route = "adMob",
        resourceId = R.string.label_admob
    )
}