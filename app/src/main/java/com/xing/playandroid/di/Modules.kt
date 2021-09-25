package com.xing.playandroid.di

import com.xing.playandroid.datasource.PlayRemoteDataSource
import com.xing.playandroid.repository.IPlayRepository
import com.xing.playandroid.repository.PlayRepositoryImpl
import com.xing.playandroid.ui.home.HomeViewModel
import com.xing.playandroid.ui.project.ProjectViewModel
import com.xing.playandroid.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // single instance of IXueRepository
    single { PlayRemoteDataSource() }

    // single instance of IXueRepository
    single<IPlayRepository> { PlayRepositoryImpl(get()) }

    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        ProjectViewModel(get())
    }
    viewModel {
        SearchViewModel(get())
    }
}