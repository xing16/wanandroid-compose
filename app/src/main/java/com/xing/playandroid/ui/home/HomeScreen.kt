package com.xing.playandroid.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xing.playandroid.entity.Article
import com.xing.playandroid.http.Result
import com.xing.playandroid.http.data
import com.xing.playandroid.ui.Screen
import com.xing.playandroid.ui.components.ArticleTextItem
import com.xing.playandroid.ui.components.ErrorView
import org.koin.androidx.compose.getViewModel
import java.net.URLEncoder

/**
 * 首页
 */
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = getViewModel<HomeViewModel>()    // inject koin
    val result by viewModel.list.collectAsState()
    when (result) {
        is Result.Loading -> {
            // show loading
            HomeList(refreshing = true)
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
        HomeList(it, result is Result.Loading) { article ->
            navController.navigate("${Screen.Web}/${URLEncoder.encode(article.link)}")
        }
    }
}


@Composable
fun HomeList(
    list: List<Article> = mutableListOf(),
    refreshing: Boolean = false,
    onItemClick: (article: Article) -> Unit = {}
) {
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
                    ArticleTextItem(article = it, onItemClick = onItemClick)
                    Divider(startIndent = 20.dp, thickness = 0.5.dp)
                }
            }
        }
    }
}

