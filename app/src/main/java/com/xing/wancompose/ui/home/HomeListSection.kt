package com.xing.wancompose.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.xing.wancompose.viewmodel.StateFlowViewModel
import com.xing.wancompose.http.Result
import com.xing.wancompose.ui.components.ArticleImageTextItem
import com.xing.wancompose.ui.components.ErrorView
import com.xing.wancompose.ui.components.LoadingView
import com.xing.wancompose.entity.Article
import com.xing.wancompose.http.data

@Composable
fun HomeListSection(viewModel: StateFlowViewModel) {
    val result by viewModel.list.collectAsState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when (result) {
            is Result.Loading -> {
                // show loading
                LoadingView(modifier = Modifier.align(Alignment.Center))
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
            Log.e("deb7ugddd", "$it")
            WanList(it)
        }
    }
}


@Composable
fun WanList(list: List<Article>) {
    LazyColumn {
        items(list) {
            ArticleImageTextItem(article = it)
        }
    }
}