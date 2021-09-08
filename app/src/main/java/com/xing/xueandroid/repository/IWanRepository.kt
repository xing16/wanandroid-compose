package com.xing.xueandroid.repository

import kotlinx.coroutines.flow.Flow
import com.xing.xueandroid.http.Result
import com.xing.xueandroid.entity.ArticleData
import com.xing.xueandroid.entity.ProjectCategory

/**
 *
 * @ProjectName: mvvmdemo
 * @Description: 作用描述 <todo>
 * @Author: xinxing.tao
 * @CreateDate: 2021/5/26 10:02
 * @UpdateUser: xinxing.tao
 * @UpdateDate: 2021/5/26 10:02
 * @UpdateRemark: 无
 */
interface IWanRepository {

    suspend fun getArticle(page: Int): Flow<Result<ArticleData>>

    suspend fun getProjectCategory(): Flow<Result<List<ProjectCategory>>>

    suspend fun getProjectCategoryList(page: Int, cid: Int): Flow<Result<ArticleData>>
}