package com.xing.xueandroid

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.xing.xueandroid.ui.Screen
import com.xing.xueandroid.ui.theme.XueandroidTheme
import com.xing.xueandroid.ui.webview.WebScreen
import com.xing.xueandroid.ui.wheel.WheelScreen

@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun XueandroidApp() {
    ProvideWindowInsets {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight
        // 设置状态栏颜色透明
        SideEffect {
            systemUiController.setStatusBarColor(Color.Transparent, darkIcons = useDarkIcons)
        }
        val navController = rememberAnimatedNavController()
        XueandroidTheme {
            NavHost(navController = navController, startDestination = Screen.Splash.route) {
                composable(route = Screen.Splash.route) {
                    SplashScreen(navController = navController)
                }
                composable(route = Screen.Main.route) {
                    MainScreen(navController)
                }
                composable(route = Screen.Wheel.route) {
                    WheelScreen()
                }
                composable(route = Screen.Web.route) {
                    WebScreen()
                }
            }
        }
    }
}