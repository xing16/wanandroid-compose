package com.xing.playandroid.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xing.playandroid.entity.ArticleData
import com.xing.playandroid.entity.SearchHistory
import com.xing.playandroid.entity.SearchHot
import com.xing.playandroid.repository.IPlayRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.xing.playandroid.http.Result

private const val TAG = "SearchViewModel"

class SearchViewModel(private val playRepository: IPlayRepository) : ViewModel() {

    private var searchPage: Int = 0
    private val _searchHistory: MutableStateFlow<SearchHistory?> = MutableStateFlow(null)
    val searchHistory: StateFlow<SearchHistory?>
        get() = _searchHistory

    private val _searchHot: MutableStateFlow<Result<List<SearchHot>>> = MutableStateFlow(Result.Success(null))
    val searchHot: StateFlow<Result<List<SearchHot>>>
        get() = _searchHot

    private val _searchResult: MutableStateFlow<Result<ArticleData>> = MutableStateFlow(Result.Success(null))
    val searchResult: StateFlow<Result<ArticleData>>
        get() = _searchResult

    fun getSearchHistory() {
        viewModelScope.launch {

        }
    }

    fun getSearchHot() {
        Log.e(TAG, "getSearchHot: ")
        viewModelScope.launch {
            playRepository.getSearchHot().collect {
                _searchHot.emit(it)
            }
        }
    }

    fun search(keyword: String) {
        viewModelScope.launch {
            playRepository.search(keyword = keyword, page = searchPage).collect {
                _searchResult.emit(it)
            }
        }
    }
}