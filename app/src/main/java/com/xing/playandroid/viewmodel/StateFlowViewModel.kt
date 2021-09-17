package com.xing.playandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xing.playandroid.entity.ArticleData
import com.xing.playandroid.repository.IPlayRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.xing.playandroid.http.Result

/**
 *
 * @ProjectName: mvvmdemo
 * @Description: 作用描述 <todo>
 * @Author: xinxing.tao
 * @CreateDate: 2021/5/31 13:21
 * @UpdateUser: xinxing.tao
 * @UpdateDate: 2021/5/31 13:21
 * @UpdateRemark: 无
 */
class StateFlowViewModel(private val playRepository: IPlayRepository) : ViewModel() {

    private val _list: MutableStateFlow<Result<ArticleData>> = MutableStateFlow(Result.Success(null))
    val list: StateFlow<Result<ArticleData>> = _list


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