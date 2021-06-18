package com.xing.wancompose.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xing.wancompose.datasource.WanRemoteDataSource
import com.xing.wancompose.repository.WanRepositoryImpl
import com.xing.wancompose.viewmodel.StateFlowViewModel
import com.xing.wancompose.viewmodel.factory.WanViewModelFactory2

/**
 * 首页
 */
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
//    val viewmodel2 = getViewModel<HomeViewModel>()    // inject koin
    val viewModel = viewModel(
        StateFlowViewModel::class.java,
        factory = WanViewModelFactory2(
            WanRepositoryImpl(WanRemoteDataSource())
        )
    )

    HomeListSection(viewModel = viewModel)

}

