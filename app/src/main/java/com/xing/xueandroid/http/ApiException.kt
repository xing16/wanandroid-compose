package com.xing.xueandroid.http

/**
 *
 * @ProjectName: mvvmdemo
 * @Description: 作用描述 <todo>
 * @Author: xinxing.tao
 * @CreateDate: 2021/5/24 10:54
 * @UpdateUser: xinxing.tao
 * @UpdateDate: 2021/5/24 10:54
 * @UpdateRemark: 无
 */
class ApiException(message: String, private val code: Int) : Exception(message)