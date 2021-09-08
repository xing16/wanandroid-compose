package com.xing.xueandroid

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.xing.xueandroid.datasource.WanRemoteDataSource
import com.xing.xueandroid.repository.WanRepositoryImpl
import com.xing.xueandroid.ui.detail.ExperimentalAnimationNav
import com.xing.xueandroid.ui.home.HomeScreen
import com.xing.xueandroid.ui.main.Screen
import com.xing.xueandroid.ui.main.mainItems
import com.xing.xueandroid.ui.profile.ProfileScreen
import com.xing.xueandroid.ui.project.ProjectScreen
import com.xing.xueandroid.ui.square.SquareScreen
import com.xing.xueandroid.ui.theme.XueandroidTheme
import com.xing.xueandroid.viewmodel.StateFlowViewModel
import com.xing.xueandroid.viewmodel.factory.WanViewModelFactory2

@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun XueandroidApp() {
    // val viewmodel2 = getViewModel<HomeViewModel>()    // inject koin
    val viewModel = viewModel(
        StateFlowViewModel::class.java,
        factory = WanViewModelFactory2(
            WanRepositoryImpl(WanRemoteDataSource())
        )
    )

    ProvideWindowInsets {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight

        // 设置状态栏颜色透明
        SideEffect {
            systemUiController.setStatusBarColor(Color.Transparent, darkIcons = useDarkIcons)
        }
        XueandroidTheme {
            val tabs = remember { mainItems }
            val navController = rememberNavController()
            val navController2 = rememberNavController()
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                bottomBar = {
                    BottomNavigation(
                        elevation = 3.dp,
                        backgroundColor = MaterialTheme.colors.background
                    ) {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        tabs.forEach { screen: Screen ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        imageVector = screen.iconVector,
                                        contentDescription = screen.route
                                    )
                                },
                                label = {
                                    Text(text = stringResource(id = screen.resourceId))
                                },
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                selectedContentColor = MaterialTheme.colors.primary,
                                unselectedContentColor = MaterialTheme.colors.onSurface,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route,

                    ) {
                    composable(Screen.Home.route) {
                        HomeScreen(navController, viewModel)
                    }
                    composable(Screen.Project.route) {
                        ProjectScreen(navController)
                    }
                    composable(Screen.Square.route) {
                        SquareScreen(navController2)
                    }
                    composable(Screen.Profile.route) {
                        ProfileScreen(navController)
                    }

                    composable("detail") {
                        ExperimentalAnimationNav(navController)
                    }
                }
            }
        }
    }


}