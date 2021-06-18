package com.xing.wancompose.http

sealed class Result<out R> {
    data class Success<out R>(val data: R?) : Result<R>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

val Result<*>.succeeded
    get() = this is Result.Success && data != null

val <R> Result<R>.data: R?
    get() = (this as? Result.Success)?.data
