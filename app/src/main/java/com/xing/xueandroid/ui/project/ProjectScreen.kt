package com.xing.xueandroid.ui.project

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.xing.xueandroid.datasource.XueRemoteDataSource
import com.xing.xueandroid.entity.Article
import com.xing.xueandroid.entity.ProjectCategory
import com.xing.xueandroid.http.Result
import com.xing.xueandroid.http.data
import com.xing.xueandroid.repository.XueRepositoryImpl
import com.xing.xueandroid.ui.components.ArticleTextItem
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalPagerApi
@Composable
fun ProjectScreen(navController: NavHostController) {
    Log.d("casdcasd", "ProjectScreen: ")
    val viewModel = getViewModel<ProjectViewModel>()
    val projectCategory by viewModel.projectCategory.collectAsState()
    when (projectCategory) {
        is Result.Loading -> {

        }
        is Result.Success -> {
            projectCategory?.data?.let { projectCategoryList: List<ProjectCategory> ->
                ProjectContent(viewModel, projectCategoryList = projectCategoryList)
            }
        }
        else -> {

        }
    }
}

@ExperimentalPagerApi
@Composable
fun ProjectContent(viewModel: ProjectViewModel, projectCategoryList: List<ProjectCategory>) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = projectCategoryList.size)
    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .wrapContentWidth()
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            },
            edgePadding = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            projectCategoryList.forEachIndexed { index, projectCategory ->
                Tab(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 10.dp),
                    content = {
                        Text(
                            text = projectCategory.name ?: "",
                            style = MaterialTheme.typography.h1
                        )
                    },
                    selected = index == pagerState.currentPage,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { page: Int ->
            projectCategoryList[page].id?.let { viewModel.getProjectList(it) }
            ProjectPagerContent(viewModel)
        }
    }
}

@Composable
fun ProjectPagerContent(viewModel: ProjectViewModel) {
    val result by viewModel.projectList.collectAsState()
    when (result) {
        is Result.Loading -> {

        }
        is Result.Error -> {

        }
        else -> {

        }
    }
    result?.data?.datas?.let { list: List<Article> ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(count = list.size) { index ->
                ArticleTextItem(article = list[index])
            }
        }
    }
}

