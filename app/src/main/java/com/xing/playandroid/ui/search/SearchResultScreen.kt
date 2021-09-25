package com.xing.playandroid.ui.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.xing.playandroid.entity.Article
import com.xing.playandroid.http.data
import com.xing.playandroid.ui.Screen
import com.xing.playandroid.ui.components.ArticleTextItem
import org.koin.androidx.compose.getViewModel
import java.net.URLEncoder

@Composable
fun SearchResultScreen(
    appNavController: NavHostController,
    keyword: String,
    searchText: MutableState<String>
) {
    val viewModel: SearchViewModel = getViewModel()
    val searchResult by viewModel.searchResult.collectAsState()
    LaunchedEffect(key1 = viewModel) {
        viewModel.search(keyword = keyword)
    }

    searchResult.data?.datas?.let {
        SearchResultList(it) { article: Article ->
            appNavController.navigate("${Screen.Web}/${URLEncoder.encode(article.link)}")
        }
    }
}


@Composable
fun SearchResultList(articles: List<Article>, onItemClick: (article: Article) -> Unit) {
    LazyColumn {
        items(articles) {
            ArticleTextItem(article = it, onItemClick = onItemClick)
            Divider(startIndent = 20.dp, thickness = 0.5.dp)
        }
    }
}