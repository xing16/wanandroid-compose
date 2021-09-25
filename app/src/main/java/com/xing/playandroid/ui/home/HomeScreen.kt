package com.xing.playandroid.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
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
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.net.URLEncoder

/**
 * 首页
 */
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            TopAppBar(
                modifier = Modifier.height(50.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Android,
                    contentDescription = null,
                    modifier = Modifier.fillMaxHeight()
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            navController.navigate(Screen.Search.route)
                        }
                        .background(
                            color = Color(0x1effffff),
                            shape = RoundedCornerShape(
                                topStart = 3.dp,
                                topEnd = 3.dp,
                                bottomEnd = 3.dp,
                                bottomStart = 3.dp
                            )
                        )
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search, contentDescription = "search",
                        tint = LocalContentColor.current.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "输入关键字搜索文章", color = LocalContentColor.current.copy(alpha = 0.5f))
                }
            }
        },
    ) {
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

