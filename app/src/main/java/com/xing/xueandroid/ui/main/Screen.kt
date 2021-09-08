package com.xing.xueandroid.ui.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Approval
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SquareFoot
import androidx.compose.ui.graphics.vector.ImageVector
import com.xing.xueandroid.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val iconVector: ImageVector) {
    object Home : Screen("home", R.string.home, Icons.Outlined.Home)
    object Project : Screen("project", R.string.project, Icons.Outlined.Approval)
    object Square : Screen("square", R.string.square, Icons.Outlined.SquareFoot)
    object Profile : Screen("profile", R.string.profile, Icons.Outlined.Person)
}

val mainItems = listOf(
    Screen.Home,
    Screen.Project,
    Screen.Square,
    Screen.Profile
)

