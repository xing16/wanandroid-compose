package com.xing.xueandroid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xing.xueandroid.entity.ArticleData
import com.xing.xueandroid.http.Result
import com.xing.xueandroid.repository.IXueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val xueRepository: IXueRepository) : ViewModel() {

    private val _list: MutableStateFlow<Result<ArticleData>?> = MutableStateFlow(null)
    val list: StateFlow<Result<ArticleData>?> = _list

    init {
        getList(0)
    }


    fun getList(page: Int) {
        viewModelScope.launch {
            xueRepository.getArticle(page).collect {
                _list.value = it
            }
        }
    }
}