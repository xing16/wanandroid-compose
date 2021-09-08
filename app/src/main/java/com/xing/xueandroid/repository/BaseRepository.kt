package com.xing.xueandroid.repository

import android.util.Log
import com.xing.xueandroid.http.ApiException
import com.xing.xueandroid.http.Response
import com.xing.xueandroid.http.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.reflect.KSuspendFunction1

/**
 *
 * @ProjectName: mvvmdemo
 * @Description: 作用描述 <todo>
 * @Author: xinxing.tao
 * @CreateDate: 2021/5/24 10:15
 * @UpdateUser: xinxing.tao
 * @UpdateDate: 2021/5/24 10:15
 * @UpdateRemark: 无
 */
open class BaseRepository(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    private val TAG = "BaseRepository"
    open suspend fun <P, R> execute(
        parameters: P,
        block: KSuspendFunction1<P, Response<R>>
    ): Flow<Result<R>> {
        Log.e(TAG, "execute: ")
        return flow {
            emit(Result.Loading)
            val response = block.invoke(parameters)
            if (response.isSuccess) {
                emit(Result.Success(response.data))
            } else {
                emit(Result.Error(ApiException(response.errMsg ?: "", response.errorCode)))
            }
        }.catch { throwable: Throwable ->
            when (throwable) {
                is ConnectException,
                is SocketTimeoutException,
                is SocketException -> {
//                    Toast.makeText(MainApplication.context, "网络连接异常", Toast.LENGTH_SHORT).show()
                }
                is UnknownHostException -> {

                }
            }
            emit(Result.Error(Exception(throwable)))
        }.flowOn(dispatcher)
    }
}


//class Test {
//    fun getres(a: String, b: String): String {
//        return "$a -----66666 -------$b"
//    }
//}
//
//
//fun test(a: String, b: String, lock: (String, String) -> String): String {
//    return lock(a, b)
//}
//
//fun main() {
//    val tt = Test()
//    println(test("11", "22", tt::getres))
//}