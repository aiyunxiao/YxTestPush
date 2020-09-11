package com.yunxiao.testpushtool.pushAPI

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * description
 * @author zhaiyaohua createBy 2020/9/8
 */
data class PushRequest(
    @SerializedName("audience")
    var audience: Audience = Audience(),
    @SerializedName("notification")
    var notification: Notification = Notification(),
    @SerializedName("platform")
    var platform: String = "android"
) : Serializable

data class Audience(
    @SerializedName("registration_id")
    var registrationId: ArrayList<String> = arrayListOf(),
    @SerializedName("alias")
    var alias: ArrayList<String> = arrayListOf()
) : Serializable

data class Notification(
    @SerializedName("android")
    var android: Android = Android()
) : Serializable

data class Android(
    @SerializedName("alert")
    var alert: String = "alert",
    @SerializedName("builder_id")
    var builderId: Int = 1,
    @SerializedName("extras")
    var extras: String = "",
    @SerializedName("large_icon")
    var largeIcon: String = "http://www.jiguang.cn/largeIcon.jpg",
    @SerializedName("title")
    var title: String = "title",
    @SerializedName("uri_action")
    var uriAction: String = "com.yunxiao.push.TRANS_ACTIVITY",
    @SerializedName("uri_activity")
    var uriActivity: String = "com.yunxiao.push.TransActivity"
) : Serializable