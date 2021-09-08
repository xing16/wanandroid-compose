package com.xing.xueandroid.ui.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.xing.xueandroid.datasource.WanRemoteDataSource
import com.xing.xueandroid.repository.WanRepositoryImpl
import com.xing.xueandroid.viewmodel.StateFlowViewModel
import com.xing.xueandroid.viewmodel.factory.WanViewModelFactory2

/**
 * 首页
 */
@Composable
fun HomeScreen(navController: NavHostController, viewModel: StateFlowViewModel) {
    HomeListSection(viewModel = viewModel)
}

