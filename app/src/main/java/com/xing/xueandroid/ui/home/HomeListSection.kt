package com.xing.xueandroid.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xing.xueandroid.viewmodel.StateFlowViewModel
import com.xing.xueandroid.http.Result
import com.xing.xueandroid.ui.components.ArticleImageTextItem
import com.xing.xueandroid.ui.components.ErrorView
import com.xing.xueandroid.ui.components.LoadingView
import com.xing.xueandroid.entity.Article
import com.xing.xueandroid.http.data
import com.xing.xueandroid.ui.components.ArticleTextItem
import com.xing.xueandroid.ui.theme.Shapes

@Composable
fun HomeListSection(viewModel: StateFlowViewModel) {
    val result by viewModel.list.collectAsState()
    when (result) {
        is Result.Loading -> {
            // show loading
            WanList(true)
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
    result.data?.datas?.let {
        Log.e("deb7ugddd", "${result is Result.Loading}")
        WanList(result is Result.Loading, it)
    }
}


@Composable
fun WanList(refreshing: Boolean, list: List<Article> = mutableListOf()) {
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
                    ArticleTextItem(
                        article = it
                    )
                }
            }
        }
    }
}