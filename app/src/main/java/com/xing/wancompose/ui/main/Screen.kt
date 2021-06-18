package com.xing.wancompose.ui.main

import androidx.annotation.StringRes
import com.xing.wancompose.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("home", R.string.home)
    object Project : Screen("project", R.string.project)
    object Square : Screen("square", R.string.square)
    object Profile : Screen("profile", R.string.profile)
}

val mainItems = listOf(
    Screen.Home,
    Screen.Project,
    Screen.Square,
    Screen.Profile
)

