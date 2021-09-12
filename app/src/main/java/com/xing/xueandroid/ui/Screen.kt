package com.xing.xueandroid.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Approval
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SquareFoot
import androidx.compose.ui.graphics.vector.ImageVector
import com.xing.xueandroid.R

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object Wheel : Screen("wheel")
    object Web : Screen("webview")
}

sealed class TabScreen(route: String, @StringRes val resourceId: Int, val iconVector: ImageVector) : Screen(route) {
    object Home : TabScreen("home", R.string.home, Icons.Outlined.Home)
    object Project : TabScreen("project", R.string.project, Icons.Outlined.Approval)
    object Square : TabScreen("square", R.string.square, Icons.Outlined.SquareFoot)
    object Profile : TabScreen("profile", R.string.profile, Icons.Outlined.Person)
}

val mainItems = listOf(
    TabScreen.Home,
    TabScreen.Project,
    TabScreen.Square,
    TabScreen.Profile
)

