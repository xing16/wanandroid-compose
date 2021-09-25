package com.xing.playandroid.ui.search

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.xing.playandroid.http.Result
import com.google.accompanist.flowlayout.FlowRow
import com.xing.playandroid.datasource.PlayRemoteDataSource
import com.xing.playandroid.entity.SearchHot
import com.xing.playandroid.repository.PlayRepositoryImpl
import com.xing.playandroid.ui.Screen
import com.xing.playandroid.viewmodel.StateFlowViewModel
import com.xing.playandroid.viewmodel.factory.PlayViewModelFactory2
import org.koin.androidx.compose.getViewModel

private const val TAG = "SearchHotHistoryScreen"

@Composable
fun SearchHotHistoryScreen(navController: NavHostController, searchText: MutableState<String>) {
    val viewModel: SearchViewModel = getViewModel()
    val searchHotResult by viewModel.searchHot.collectAsState()
    LaunchedEffect(key1 = viewModel) {
        viewModel.getSearchHot()      // load remote data
    }
    LazyColumn {
        // column header
        item {
            SearchHotTitle()
            SearchHotSection(
                navController = navController,
                searchHotResult = searchHotResult,
                searchText = searchText
            )
            Spacer(modifier = Modifier.height(10.dp))
            SearchHistoryTitle()
        }
        // column item
        items(count = 20) {
            SearchHistoryItem({}, {})
            Divider(startIndent = 40.dp, thickness = 0.5.dp)
        }
    }
}


@Composable
fun SearchHotTitle() {
    Text(
        text = "热门搜索",
        modifier = Modifier.padding(13.dp),
        style = MaterialTheme.typography.h2
    )
}

@Composable
fun SearchHistoryTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "历史搜索",
            modifier = Modifier.padding(start = 0.dp, end = 0.dp, top = 13.dp, bottom = 6.dp),
            style = MaterialTheme.typography.h2
        )
        Icon(
            imageVector = Icons.Filled.DeleteForever,
            contentDescription = "clear",
            tint = LocalContentColor.current.copy(alpha = 0.35f)
        )
    }
}

@Composable
fun SearchHotSection(
    navController: NavHostController,
    searchHotResult: Result<List<SearchHot>>,
    searchText: MutableState<String>
) {
    Log.e(TAG, "SearchHotSection: 22222222 ${searchHotResult}")
    val context = LocalContext.current.applicationContext
    when (searchHotResult) {
        is Result.Loading -> {

        }
        is Result.Error -> {

        }
        is Result.Success -> {
            searchHotResult.data?.let {
                SearchHotContent(it) { searchHot: SearchHot ->
                    searchText.value = searchHot.name
                    // 隐藏键盘
                    navController.navigate("${Screen.SearchResult.route}/${searchHot.name}")
                }
            }
        }
    }
}

@Composable
private fun SearchHotContent(list: List<SearchHot>, onSearchHotClick: (SearchHot) -> Unit) {
    FlowRow(
        modifier = Modifier.padding(horizontal = 13.dp),
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp
    ) {
        list.forEach {
            Text(text = it.name,
                modifier = Modifier
                    .wrapContentSize()
                    .border(
                        width = 1.dp,
                        color = Color(0xFFeeeeee),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .clip(shape = RoundedCornerShape(15.dp))
                    .clickable {
                        onSearchHotClick(it)
                    }
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }
}


@Composable
private fun SearchHistoryItem(onSearchHistoryItem: () -> Unit, onRemoveHistoryItem: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSearchHistoryItem()
            }
            .padding(horizontal = 13.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.History,
                contentDescription = "history",
                tint = LocalContentColor.current.copy(alpha = 0.2f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "历史搜索")
        }
        Icon(
            modifier = Modifier.clickable {
                onRemoveHistoryItem()
            },
            imageVector = Icons.Filled.Close,
            contentDescription = "remove",
            tint = LocalContentColor.current.copy(alpha = 0.2f)
        )
    }
}
