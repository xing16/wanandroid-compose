package com.xing.xueandroid.repository

import com.xing.xueandroid.datasource.XueRemoteDataSource
import com.xing.xueandroid.http.Result
import com.xing.xueandroid.entity.ArticleData
import com.xing.xueandroid.entity.ProjectCategory
import com.xing.xueandroid.params.Page
import kotlinx.coroutines.flow.Flow

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
class XueRepositoryImpl(
    private val remoteDataSource: XueRemoteDataSource
) : BaseRepository(), IXueRepository {

    override suspend fun getArticle(page: Int): Flow<Result<ArticleData>> {
        return getArticleRemote2(page)
    }

    override suspend fun getProjectCategory(): Flow<Result<List<ProjectCategory>>> {
        return execute("", remoteDataSource::getProjectCategory)
    }

    override suspend fun getProjectList(cid: Int, page: Int): Flow<Result<ArticleData>> {
        return execute(Page(page, cid), remoteDataSource::getProjectList)
    }


    private suspend fun getArticleRemote2(page: Int): Flow<Result<ArticleData>> {
        return execute(page, remoteDataSource::getArticles)
    }

}