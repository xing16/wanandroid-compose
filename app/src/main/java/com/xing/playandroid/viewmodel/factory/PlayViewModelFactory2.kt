package com.xing.playandroid.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xing.playandroid.viewmodel.StateFlowViewModel
import com.xing.playandroid.repository.IPlayRepository
import com.xing.playandroid.ui.search.SearchViewModel

/**
 *
 * @ProjectName: mvvmdemo
 * @Description: 作用描述 <todo>
 * @Author: xinxing.tao
 * @CreateDate: 2021/4/13 10:54
 * @UpdateUser: xinxing.tao
 * @UpdateDate: 2021/4/13 10:54
 * @UpdateRemark: 无
 */
class PlayViewModelFactory2(
    private val iPlayRepository: IPlayRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == StateFlowViewModel::class.java) {
            return StateFlowViewModel(iPlayRepository) as T
        } else if (modelClass == SearchViewModel::class.java) {
            return SearchViewModel(iPlayRepository) as T
        }
        return super.create(modelClass)
    }
}