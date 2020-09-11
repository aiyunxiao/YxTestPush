package com.yunxiao.testpushtool.pushAPI

import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * description
 * @author zhaiyaohua createBy 2020/9/8
 */
internal interface PushService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("push")
    fun pushDebug(
        @Header("Authorization") author: String,
        @Body requestBody: RequestBody
    ): Flowable<String>
}
