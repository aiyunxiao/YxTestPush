package com.yunxiao.testpushtool.pushAPI

import android.content.Context
import okhttp3.Interceptor

/**
 * description
 * @author zhaiyaohua createBy 2020/9/8
 */
object PushToolConfig {
    internal var listener: (String) -> Unit = {}
    internal fun setResponseListener(listener: (String) -> Unit) {
        this.listener = listener

    }

    fun init(context: Context): PushToolConfig {

        return this
    }

    fun initRegistrationId(id: String?): PushToolConfig {

        return this
    }

    fun initAlias(alise: String?): PushToolConfig {

        return this
    }

    fun configAuth(
        testStr: String?,
        releaseStr: String?
    ): PushToolConfig {

        return this
    }

    fun addInterceptor(interceptor: Interceptor): PushToolConfig {

        return this
    }

    fun setPushExtras(obj: Any?): PushToolConfig {

        return this
    }

    fun savePushContent(content: String): PushToolConfig {

        return this
    }

    fun getPushContent(): String {
        return ""

    }

    fun getTestAuth(): String {
        return ""
    }

    fun getReleaseAuth(): String {
        return  ""
    }
}