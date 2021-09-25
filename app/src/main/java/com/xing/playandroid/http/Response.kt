package com.xing.playandroid.http

data class Response<T>(
    val data: T? = null,
    val errorCode: Int = 0,
    val errMsg: String? = null
) {
    val isSuccess: Boolean
        get() = errorCode == 0
}