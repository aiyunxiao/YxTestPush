package com.yunxiao.testpushtool.pushAPI

import android.content.Context
import android.util.Base64
import com.google.gson.Gson
import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor
import com.yunxiao.testpushtool.Constants
import com.yunxiao.testpushtool.PushGsonUtill
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * description
 * @author zhaiyaohua createBy 2020/9/8
 */
object PushToolConfig {
    private const val CONNECT_TIME = 15L
    private const val READ_WRITE_TIMEOUT = 15L
    private val resultInterceptor: Interceptor = Interceptor { chain ->
        var response = chain.proceed(chain.request())
        listener(response.body()?.string() ?: "")
        response
    }
    private val okBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
            .addInterceptor(LogInterceptor())
            .addNetworkInterceptor(resultInterceptor)
            .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
            .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
    }
    internal var listener: (String) -> Unit = {}
    internal fun setResponseListener(listener: (String) -> Unit) {
        this.listener = listener

    }

    fun configAuth(
        context: Context,
        testStr: String?,
        releaseStr: String?
    ): PushToolConfig {
        var testAuth = testStr?.run {
            "Basic " + Base64.encodeToString(this.toByteArray(), Base64.NO_WRAP)
        } ?: ""

        var releaseAuth = releaseStr?.run {
            "Basic " + Base64.encodeToString(this.toByteArray(), Base64.NO_WRAP)
        } ?: ""
        context.getSharedPreferences(Constants.PUSH_DATA, Context.MODE_PRIVATE).edit().also {
            it.putString(Constants.TEST_AUTH, testAuth)
            it.putString(Constants.RELEASE_AUTH, releaseAuth)
            it.apply()
        }
        return this
    }

    fun addInterceptor(interceptor: Interceptor): PushToolConfig {
        okBuilder.addNetworkInterceptor(interceptor)
        return this
    }

    fun setPushExtras(context: Context, obj: Any?): PushToolConfig {
        var content = PushRequest().apply {
            notification.android.extras = Gson().toJson(obj)
        }
        context.getSharedPreferences(Constants.PUSH_DATA, Context.MODE_PRIVATE).edit().also {
            it.putString(Constants.PUSH_CONTENT, PushGsonUtill.toJson(content))
            it.apply()
        }
        return this
    }

    fun savePushContent(context: Context, content: String): PushToolConfig {
        context.getSharedPreferences(Constants.PUSH_DATA, Context.MODE_PRIVATE).edit().also {
            it.putString(Constants.PUSH_CONTENT, content)
            it.apply()

        }
        return this
    }

    fun getPushContent(context: Context): String {
        return context.getSharedPreferences(Constants.PUSH_DATA, Context.MODE_PRIVATE)
            .getString(Constants.PUSH_CONTENT, "") ?: ""
    }

    fun getTestAuth(context: Context): String {
        return context.getSharedPreferences(Constants.PUSH_DATA, Context.MODE_PRIVATE)
            .getString(Constants.TEST_AUTH, "") ?: ""
    }

    fun getReleaseAuth(context: Context): String {
        return context.getSharedPreferences(Constants.PUSH_DATA, Context.MODE_PRIVATE)
            .getString(Constants.RELEASE_AUTH, "") ?: ""
    }


    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://bjapi.push.jiguang.cn/v3/")
            .addConverterFactory(GsonConverterFactory.create()) // 添加Gosn转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加RxJava2支持
            .client(okBuilder.build())
            .build()
    }
}