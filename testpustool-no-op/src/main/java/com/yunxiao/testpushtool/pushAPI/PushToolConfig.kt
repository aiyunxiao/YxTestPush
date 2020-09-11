package com.yunxiao.testpushtool.pushAPI

import android.content.Context
import okhttp3.Interceptor

/**
 * description
 * @author zhaiyaohua createBy 2020/9/8
 */
object PushToolConfig {
    fun configAuth(
        context: Context,
        testStr: String?,
        releaseStr: String?
    ): PushToolConfig {
        return this
    }

    fun addInterceptor(interceptor: Interceptor): PushToolConfig {
        return this
    }

    fun setPushExtras(context: Context, obj: Any?): PushToolConfig {
        return this
    }

    fun savePushContent(context: Context, content: String): PushToolConfig {

        return this
    }

    fun getPushContent(context: Context): String {
        return ""
    }

    fun getTestAuth(context: Context): String {
        return ""
    }

    fun getReleaseAuth(context: Context): String {
        return ""
    }
}