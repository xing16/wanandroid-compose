package com.xing.playandroid.repository

import com.xing.playandroid.datasource.PlayRemoteDataSource
import com.xing.playandroid.http.Result
import com.xing.playandroid.entity.ArticleData
import com.xing.playandroid.entity.ProjectCategory
import com.xing.playandroid.entity.SearchHot
import com.xing.playandroid.params.Page
import com.xing.playandroid.params.SearchParam
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
class PlayRepositoryImpl(
    private val remoteDataSource: PlayRemoteDataSource
) : BaseRepository(), IPlayRepository {

    override suspend fun getArticle(page: Int): Flow<Result<ArticleData>> {
        return getArticleRemote2(page)
    }

    override suspend fun getProjectCategory(): Flow<Result<List<ProjectCategory>>> {
        return execute("", remoteDataSource::getProjectCategory)
    }

    override suspend fun getProjectList(cid: Int, page: Int): Flow<Result<ArticleData>> {
        return execute(Page(page, cid), remoteDataSource::getProjectList)
    }

    override suspend fun getSearchHot(): Flow<Result<List<SearchHot>>> {
        return execute("", remoteDataSource::getSearchHot)
    }

    override suspend fun search(keyword: String, page: Int): Flow<Result<ArticleData>> {
        return execute(SearchParam(keyword, page), remoteDataSource::search)
    }

    private suspend fun getArticleRemote2(page: Int): Flow<Result<ArticleData>> {
        return execute(page, remoteDataSource::getArticles)
    }
}