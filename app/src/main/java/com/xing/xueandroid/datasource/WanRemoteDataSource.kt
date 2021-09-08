package com.xing.xueandroid.datasource

import com.xing.xueandroid.http.ApiService
import com.xing.xueandroid.http.RetrofitClient
import com.xing.xueandroid.http.Response
import com.xing.xueandroid.entity.ArticleData
import com.xing.xueandroid.entity.ProjectCategory
import com.xing.xueandroid.http.Result
import com.xing.xueandroid.params.Page
import kotlinx.coroutines.flow.Flow

/**
 *
 * @ProjectName: mvvmdemo
 * @Description: 作用描述 <todo>
 * @Author: xinxing.tao
 * @CreateDate: 2021/5/24 10:29
 * @UpdateUser: xinxing.tao
 * @UpdateDate: 2021/5/24 10:29
 * @UpdateRemark: 无
 */
class WanRemoteDataSource {

    suspend fun getArticles(page: Int): Response<ArticleData> {
        return RetrofitClient.create(ApiService::class.java).getHomeArticleList(page)
    }

    suspend fun getProjectCategory(ss: String): Response<List<ProjectCategory>> {
        return RetrofitClient.create(ApiService::class.java).getProjectCategory()
    }

    suspend fun getProjectCategoryList(page: Page): Response<ArticleData> {
        return RetrofitClient.create(ApiService::class.java).getProjectCategoryList(page.page, page.id)
    }

}