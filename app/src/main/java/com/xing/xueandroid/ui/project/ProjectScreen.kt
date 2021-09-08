package com.xing.xueandroid.ui.project

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.xing.xueandroid.datasource.WanRemoteDataSource
import com.xing.xueandroid.entity.ProjectCategory
import com.xing.xueandroid.http.Result
import com.xing.xueandroid.http.data
import com.xing.xueandroid.repository.WanRepositoryImpl
import com.xing.xueandroid.viewmodel.ProjectViewModel
import com.xing.xueandroid.viewmodel.StateFlowViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun ProjectScreen(navController: NavHostController, viewModel: ProjectViewModel = ProjectViewModel(WanRepositoryImpl(WanRemoteDataSource()))) {
    Log.d("casdcasd", "ProjectScreen: ")
    val projectCategory: Result<List<ProjectCategory>> by viewModel.projectCategory.collectAsState()
    when (projectCategory) {
        is Result.Loading -> {

        }
        is Result.Success -> {
            projectCategory.data?.let { projectCategoryList: List<ProjectCategory> ->
                ProjectContent(projectCategoryList = projectCategoryList)
            }
        }
        else -> {

        }
    }
}

@ExperimentalPagerApi
@Composable
fun ProjectContent(projectCategoryList: List<ProjectCategory>) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = projectCategoryList.size)
    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            projectCategoryList.forEachIndexed { index, projectCategory ->
                Tab(
                    content = {
                        Text(text = projectCategory.name ?: "")
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
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}

