package com.xing.playandroid

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.xing.playandroid.ui.Screen
import com.xing.playandroid.ui.theme.PlayandroidTheme
import com.xing.playandroid.ui.webview.WebViewScreen
import com.xing.playandroid.ui.wheel.WheelScreen

@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun PlayApp() {
    ProvideWindowInsets {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight
        // 设置状态栏颜色透明
        SideEffect {
            systemUiController.setStatusBarColor(Color.Transparent, darkIcons = useDarkIcons)
        }
        val navController = rememberAnimatedNavController()
        PlayandroidTheme {
            AnimatedNavHost(navController = navController, startDestination = Screen.Splash.route) {
                composable(route = Screen.Splash.route) {
                    SplashScreen(navController = navController)
                }
                composable(route = Screen.Main.route) {
                    MainScreen(navController)
                }
                composable(route = Screen.Wheel.route) {
                    WheelScreen()
                }
                composable(
                    route = "${Screen.Web}/{url}",
                    arguments = listOf(
                        navArgument("url") {
                            type = NavType.StringType
                            nullable = false
                            defaultValue = ""
                        }
                    ),
                    enterTransition = { _, _ ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(400))
                    },
                    exitTransition = { _, _ ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(400))
                    }
                ) {
                    WebViewScreen(navController, it.arguments?.getString("url"))
                }
            }
        }
    }
}