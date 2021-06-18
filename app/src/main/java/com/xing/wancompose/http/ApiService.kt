package com.xing.wancompose.http

import com.xing.wancompose.entity.ArticleData
import retrofit2.http.GET
import retrofit2.http.Path

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

    @GET("article/list/{page}/json")
    suspend fun getArticles(@Path("page") page: Int): Response<ArticleData>
}