package com.xing.playandroid.http

import com.xing.playandroid.entity.ArticleData
import com.xing.playandroid.entity.ProjectCategory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * @ProjectName: mvvmdemo
 * @Description: 作用描述 <todo>
 * @Author: xinxing.tao
 * @CreateDate: 2021/4/13 9:41
 * @UpdateUser: xinxing.tao
 * @UpdateDate: 2021/4/13 9:41
 * @UpdateRemark: 无
 */
interface ApiService {

    /**
     * 首页列表
     */
    @GET("article/list/{page}/json")
    suspend fun getHomeArticleList(@Path("page") page: Int): Response<ArticleData>


    /**
     * 项目分类
     */
    @GET("project/tree/json")
    suspend fun getProjectCategory(): Response<List<ProjectCategory>>


    /**
     * 项目列表
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectCategoryList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): Response<ArticleData>


}