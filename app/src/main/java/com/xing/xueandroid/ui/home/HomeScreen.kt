package com.xing.xueandroid.ui.home

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xing.xueandroid.entity.Article
import com.xing.xueandroid.http.Result
import com.xing.xueandroid.http.data
import com.xing.xueandroid.ui.Screen
import com.xing.xueandroid.ui.components.ArticleTextItem
import com.xing.xueandroid.ui.components.ErrorView
import org.koin.androidx.compose.getViewModel

/**
 * 首页
 */
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = getViewModel<HomeViewModel>()    // inject koin
    HomeListSection(navController = navController, viewModel = viewModel)
}

@Composable
fun HomeListSection(navController: NavHostController, viewModel: HomeViewModel) {
    val result by viewModel.list.collectAsState()
    when (result) {
        is Result.Loading -> {
            // show loading
            WanList(navController = navController, refreshing = true)
        }
        is Result.Error -> {
            // show error
            ErrorView {
            }
        }
        is Result.Success -> {
            // show success
        }
    }
    result?.data?.datas?.let {
        Log.e("deb7ugddd", "${result is Result.Loading}")
        WanList(navController, result is Result.Loading, it)
    }
}


@Composable
fun WanList(navController: NavHostController, refreshing: Boolean, list: List<Article> = mutableListOf()) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = {
        }
    ) {
        LazyColumn {
            if (refreshing) {
                items(10) {
                    ArticleTextItem(
                        childModifier = Modifier.placeholder(
                            visible = refreshing,
                            shape = MaterialTheme.shapes.medium,
                            highlight = PlaceholderHighlight.shimmer()
                        ),
                        article = Article(title = "")
                    )
                }
            } else {
                items(list) {
                    ArticleTextItem(article = it) {
                        navController.navigate(Screen.Web.route)
                    }
                }
            }
        }
    }
}

