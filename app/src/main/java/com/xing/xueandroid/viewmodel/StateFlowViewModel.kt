package com.xing.xueandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xing.xueandroid.entity.ArticleData
import com.xing.xueandroid.repository.IXueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.xing.xueandroid.http.Result

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
class StateFlowViewModel(private val xueRepository: IXueRepository) : ViewModel() {

    private val _list: MutableStateFlow<Result<ArticleData>> = MutableStateFlow(Result.Success(null))
    val list: StateFlow<Result<ArticleData>> = _list


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