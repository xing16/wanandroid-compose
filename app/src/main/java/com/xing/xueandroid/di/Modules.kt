package com.xing.xueandroid.di

import com.xing.xueandroid.datasource.XueRemoteDataSource
import com.xing.xueandroid.repository.IXueRepository
import com.xing.xueandroid.repository.XueRepositoryImpl
import com.xing.xueandroid.ui.home.HomeViewModel
import com.xing.xueandroid.ui.project.ProjectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // single instance of IXueRepository
    single { XueRemoteDataSource() }

    // single instance of IXueRepository
    single<IXueRepository> { XueRepositoryImpl(get()) }

    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        ProjectViewModel(get())
    }
}