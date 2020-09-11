package com.yunxiao.testpushtool

import com.google.gson.Gson
import com.yunxiao.testpushtool.pushAPI.PushRequest
import com.yunxiao.testpushtool.pushAPI.PushService
import com.yunxiao.testpushtool.pushAPI.PushToolConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import okhttp3.MediaType
import okhttp3.RequestBody


/**
 * description
 * @author zhaiyaohua createBy 2020/9/8
 */
internal class Presenter(var view: PushContact.PushView) : PushContact.PushPresent {
    private val pushService: PushService = PushToolConfig.retrofit.create(PushService::class.java)

    override fun pushStuDebug(author: String, requestBody: PushRequest) {
        var strEntity = Gson().toJson(requestBody)
        var jsonBody =
            RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), strEntity)
        view.addDisposable(
            pushService.pushDebug(
                author, jsonBody
            ).subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSubscriber<String>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: String?) {

                    }

                    override fun onError(t: Throwable?) {

                    }

                })
        )
    }
}