package com.example.adsnippet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adsnippet.navigation.Screen
import com.example.adsnippet.screen.AdBannerScreen
import com.example.adsnippet.screen.AdInterstitialScreen
import com.example.adsnippet.theme.AdSnippetTheme
import com.google.android.gms.ads.MobileAds

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this)

        setContent { App() }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()

        AdSnippetTheme {
            NavHost(
                navController = navController,
                startDestination = Screen.AdMobBanner
            ) {
                composable<Screen.AdMobBanner> {
                    AdBannerScreen(
                        onNextClick = {
                            navController.navigate(Screen.AdMobInterstitial)
                        }
                    )
                }

                composable<Screen.AdMobInterstitial> {
                    AdInterstitialScreen(this@MainActivity)
                }
            }
        }
    }
}