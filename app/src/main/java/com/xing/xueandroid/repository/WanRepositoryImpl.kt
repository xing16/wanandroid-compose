package com.xing.xueandroid.repository

import com.xing.xueandroid.http.Result
import com.xing.xueandroid.entity.ArticleData
import com.xing.xueandroid.datasource.WanRemoteDataSource
import com.xing.xueandroid.entity.ProjectCategory
import com.xing.xueandroid.http.Response
import com.xing.xueandroid.params.Page
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KSuspendFunction0

/**
 *
 * @ProjectName: mvvmdemo
 * @Description: 作用描述 <todo>
 * @Author: xinxing.tao
 * @CreateDate: 2021/5/26 10:03
 * @UpdateUser: xinxing.tao
 * @UpdateDate: 2021/5/26 10:03
 * @UpdateRemark: 无
 */
class WanRepositoryImpl(
    private val remoteDataSource: WanRemoteDataSource
) : BaseRepository(), IWanRepository {

    override suspend fun getArticle(page: Int): Flow<Result<ArticleData>> {
        return getArticleRemote2(page)
    }

    override suspend fun getProjectCategory(): Flow<Result<List<ProjectCategory>>> {
        return execute("", remoteDataSource::getProjectCategory)
    }

    override suspend fun getProjectCategoryList(page: Int, cid: Int): Flow<Result<ArticleData>> {
        return execute(Page(page, cid), remoteDataSource::getProjectCategoryList)
    }


    private suspend fun getArticleRemote2(page: Int): Flow<Result<ArticleData>> {
        return execute(page, remoteDataSource::getArticles)
    }

}