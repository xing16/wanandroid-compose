package com.xing.playandroid

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.xing.playandroid.ui.detail.ExperimentalAnimationNav
import com.xing.playandroid.ui.home.HomeScreen
import com.xing.playandroid.ui.home.HomeViewModel
import com.xing.playandroid.ui.TabScreen
import com.xing.playandroid.ui.mainItems
import com.xing.playandroid.ui.profile.ProfileScreen
import com.xing.playandroid.ui.project.ProjectScreen
import com.xing.playandroid.ui.square.SquareScreen
import org.koin.androidx.compose.getViewModel

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun MainScreen(appNavController: NavHostController) {
//    val viewModel = viewModel(
//        StateFlowViewModel::class.java,
//        factory = WanViewModelFactory2(
//            XueRepositoryImpl(WanRemoteDataSource())
//        )
//    )
    val viewModel = getViewModel<HomeViewModel>()
    val tabs = remember { mainItems }
    val navController = rememberNavController()
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
                tabs.forEach { screen: TabScreen ->
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
            startDestination = TabScreen.Home.route
        ) {
            composable(TabScreen.Home.route) {
                HomeScreen(appNavController)
            }
            composable(TabScreen.Project.route) {
                ProjectScreen(navController)
            }
            composable(TabScreen.Square.route) {
                SquareScreen(appNavController)
            }
            composable(TabScreen.Profile.route) {
                ProfileScreen(appNavController)
            }
            composable("detail") {
                ExperimentalAnimationNav(navController)
            }
        }
    }
}