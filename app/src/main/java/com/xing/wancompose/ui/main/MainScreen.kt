package com.xing.wancompose.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.xing.wancompose.global.KEY_ROUTE
import com.xing.wancompose.ui.home.HomeScreen
import com.xing.wancompose.ui.profile.ProfileScreen
import com.xing.wancompose.ui.project.ProjectScreen
import com.xing.wancompose.ui.square.SquareScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val curIndex by remember {
        mutableStateOf(0)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(elevation = 3.dp) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                mainItems.forEach { screen: Screen ->
                    BottomNavigationItem(
                        selected = (currentRoute == screen.route),
                        onClick = {
                            navController.navigate(screen.route) {
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = Icons.Outlined.Email, contentDescription = "")
                        },
                        label = {
                            Text(text = stringResource(id = screen.resourceId))
                        }
                    )
                }
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Project.route) {
                ProjectScreen()
            }
            composable(Screen.Square.route) {
                SquareScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}