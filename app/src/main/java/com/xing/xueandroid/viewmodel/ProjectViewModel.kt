package com.xing.xueandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xing.xueandroid.entity.ProjectCategory
import com.xing.xueandroid.http.Result
import com.xing.xueandroid.repository.IWanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProjectViewModel(private val wanRepository: IWanRepository) : ViewModel() {

    private val _projectCategory: MutableStateFlow<Result<List<ProjectCategory>>> = MutableStateFlow(Result.Success(null))
    var projectCategory: StateFlow<Result<List<ProjectCategory>>> = _projectCategory

    init {
        getProjectCategory()
    }

    private fun getProjectCategory() {
        viewModelScope.launch {
            wanRepository.getProjectCategory().collect {
                _projectCategory.emit(it)
            }
        }
    }

}