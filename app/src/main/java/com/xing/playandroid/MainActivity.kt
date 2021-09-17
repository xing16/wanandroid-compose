package com.xing.playandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.view.WindowCompat
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        // false: 内容延伸至状态栏，导航栏内， true： 不延伸, 例如顶部图片页面，图片可以延伸至状态栏中
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            PlayApp()
        }
    }
}