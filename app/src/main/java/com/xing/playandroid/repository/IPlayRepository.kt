package com.xing.playandroid.repository

import kotlinx.coroutines.flow.Flow
import com.xing.playandroid.http.Result
import com.xing.playandroid.entity.ArticleData
import com.xing.playandroid.entity.ProjectCategory
import com.xing.playandroid.entity.SearchHot

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
interface IPlayRepository {

    suspend fun getArticle(page: Int): Flow<Result<ArticleData>>

    suspend fun getProjectCategory(): Flow<Result<List<ProjectCategory>>>

    suspend fun getProjectList(cid: Int, page: Int): Flow<Result<ArticleData>>

    suspend fun getSearchHot(): Flow<Result<List<SearchHot>>>

    suspend fun search(keyword: String, page: Int): Flow<Result<ArticleData>>
}