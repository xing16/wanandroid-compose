package com.xing.playandroid.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Approval
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SquareFoot
import androidx.compose.ui.graphics.vector.ImageVector
import com.xing.playandroid.R

sealed class Screen(val route: String) {
    object Splash : Screen("play://splash")
    object Main : Screen("play://main")
    object Wheel : Screen("play://wheel")
    object Web : Screen("play://webView")
    object Search : Screen("play://search")
    object SearchHistory : Screen("play://search/history")
    object SearchResult : Screen("play://search/result")
    object Login : Screen("play://login")
}

sealed class TabScreen(route: String, @StringRes val resourceId: Int, val iconVector: ImageVector) : Screen(route) {
    object Home : TabScreen("play://main/home", R.string.home, Icons.Outlined.Home)
    object Project : TabScreen("play://main/project", R.string.project, Icons.Outlined.Approval)
    object Square : TabScreen("play://main/square", R.string.square, Icons.Outlined.SquareFoot)
    object Profile : TabScreen("play://main/profile", R.string.profile, Icons.Outlined.Person)
}

val mainItems = listOf(
    TabScreen.Home,
    TabScreen.Project,
    TabScreen.Square,
    TabScreen.Profile
)

