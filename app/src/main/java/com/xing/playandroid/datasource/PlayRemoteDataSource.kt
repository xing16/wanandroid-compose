package com.xing.playandroid.datasource

import com.xing.playandroid.http.ApiService
import com.xing.playandroid.http.RetrofitClient
import com.xing.playandroid.http.Response
import com.xing.playandroid.entity.ArticleData
import com.xing.playandroid.entity.ProjectCategory
import com.xing.playandroid.entity.SearchHot
import com.xing.playandroid.params.Page
import com.xing.playandroid.params.SearchParam

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
class PlayRemoteDataSource {

    suspend fun getArticles(page: Int): Response<ArticleData> {
        return RetrofitClient.create(ApiService::class.java).getHomeArticleList(page)
    }

    suspend fun getProjectCategory(ss: String): Response<List<ProjectCategory>> {
        return RetrofitClient.create(ApiService::class.java).getProjectCategory()
    }

    suspend fun getProjectList(page: Page): Response<ArticleData> {
        return RetrofitClient.create(ApiService::class.java).getProjectCategoryList(page.page, page.cid)
    }

    suspend fun getSearchHot(ss: String): Response<List<SearchHot>> {
        return RetrofitClient.create(ApiService::class.java).getSearchHot()
    }

    suspend fun search(searchParam: SearchParam): Response<ArticleData> {
        return RetrofitClient.create(ApiService::class.java).search(searchParam.k, searchParam.page)
    }

}