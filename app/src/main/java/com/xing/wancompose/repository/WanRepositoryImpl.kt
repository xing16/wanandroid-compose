package com.xing.wancompose.repository

import com.xing.wancompose.http.Result
import com.xing.wancompose.entity.ArticleData
import com.xing.wancompose.datasource.WanRemoteDataSource
import com.xing.wancompose.repository.BaseRepository
import com.xing.wancompose.repository.IWanRepository
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
class WanRepositoryImpl(
    private val remoteDataSource: WanRemoteDataSource
) : BaseRepository(), IWanRepository {

    override suspend fun getArticle(page: Int): Flow<Result<ArticleData>> {
        return getArticleRemote2(page)
    }

    private suspend fun getArticleRemote2(page: Int): Flow<Result<ArticleData>> {
        return execute(page, remoteDataSource::getArticles)
    }
}