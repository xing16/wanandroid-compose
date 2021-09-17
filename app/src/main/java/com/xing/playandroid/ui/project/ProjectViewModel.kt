package com.xing.playandroid.ui.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xing.playandroid.entity.ArticleData
import com.xing.playandroid.entity.ProjectCategory
import com.xing.playandroid.http.Result
import com.xing.playandroid.repository.IPlayRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProjectViewModel(private val playRepository: IPlayRepository) : ViewModel() {

    private val _projectCategory: MutableStateFlow<Result<List<ProjectCategory>>?> = MutableStateFlow(null)
    var projectCategory: StateFlow<Result<List<ProjectCategory>>?> = _projectCategory

    private val _projectList: MutableStateFlow<Result<ArticleData>?> = MutableStateFlow(null)
    var projectList: StateFlow<Result<ArticleData>?> = _projectList
    var page = 1


    init {
        getProjectCategory()
    }

    private fun getProjectCategory() {
        viewModelScope.launch {
            playRepository.getProjectCategory().collect {
                _projectCategory.emit(it)
            }
        }
    }

    fun getProjectList(cid: Int) {
        viewModelScope.launch {
            playRepository.getProjectList(cid, page).collect {
                _projectList.emit(it)
            }
        }
    }
}