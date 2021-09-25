package com.xing.playandroid.ui.project

import android.text.TextUtils
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.xing.playandroid.entity.Article
import com.xing.playandroid.entity.ProjectCategory
import com.xing.playandroid.http.Result
import com.xing.playandroid.http.data
import com.xing.playandroid.ui.Screen
import com.xing.playandroid.ui.components.ArticleImageTextItem
import com.xing.playandroid.ui.components.ArticleTextItem
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.net.URLEncoder

@ExperimentalPagerApi
@Composable
fun ProjectScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        val viewModel = getViewModel<ProjectViewModel>()
        val projectCategory by viewModel.projectCategory.collectAsState()
        when (projectCategory) {
            is Result.Loading -> {

            }
            is Result.Success -> {
                projectCategory?.data?.let { result: List<ProjectCategory> ->
                    ProjectContent(
                        navController = navController,
                        viewModel = viewModel,
                        projectCategoryList = result
                    )
                }
            }
            else -> {

            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun ProjectContent(
    navController: NavHostController,
    viewModel: ProjectViewModel = getViewModel(),
    projectCategoryList: List<ProjectCategory>
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = projectCategoryList.size)
    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            },
            edgePadding = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
        ) {
            projectCategoryList.forEachIndexed { index, projectCategory ->
                Tab(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 10.dp),
                    content = {
                        Text(
                            text = projectCategory.name ?: "",
                            style = MaterialTheme.typography.h2
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
                .fillMaxWidth()
                .weight(1f)
        ) { page: Int ->
//            Card {
//                Box(Modifier.fillMaxSize()) {
//                    Text(
//                        text = "Page: ${projectCategoryList[page].id}",
//                        style = MaterialTheme.typography.h4,
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//            }
            ProjectPagerContent(cid = projectCategoryList[page].id ?: 0) {
                navController.navigate("${Screen.Web}/${URLEncoder.encode(it.link)}")
            }
        }
    }
}

@Composable
fun ProjectPagerContent(
    viewModel: ProjectViewModel = getViewModel(),
    cid: Int,
    onItemClick: (Article) -> Unit = {}
) {
    LaunchedEffect(key1 = true, block = {
        viewModel.getProjectList(cid = cid)
    })
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
                val article = list[index]
                if (TextUtils.isEmpty(article.envelopePic)) {
                    ArticleTextItem(article = article, onItemClick = onItemClick)
                } else {
                    ArticleImageTextItem(article = article, onItemClick = onItemClick)
                }
                Divider(startIndent = 20.dp, thickness = 0.5.dp)
            }
        }
    }
}

