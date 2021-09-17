package com.xing.playandroid.http

data class Response<T>(val data: T?, val errorCode: Int, val errMsg: String?) {

    val isSuccess: Boolean
        get() = errorCode == 0
}