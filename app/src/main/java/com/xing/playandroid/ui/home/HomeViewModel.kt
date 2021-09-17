package com.xing.playandroid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xing.playandroid.entity.ArticleData
import com.xing.playandroid.http.Result
import com.xing.playandroid.repository.IPlayRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val playRepository: IPlayRepository) : ViewModel() {

    private val _list: MutableStateFlow<Result<ArticleData>?> = MutableStateFlow(null)
    val list: StateFlow<Result<ArticleData>?> = _list

    init {
        getList(0)
    }


    fun getList(page: Int) {
        viewModelScope.launch {
            playRepository.getArticle(page).collect {
                _list.value = it
            }
        }
    }
}