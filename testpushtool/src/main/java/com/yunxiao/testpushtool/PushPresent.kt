package com.yunxiao.testpushtool

import com.yunxiao.testpushtool.pushAPI.PushRequest

/**
 * description
 * @author zhaiyaohua createBy 2020/9/8
 */
interface PushContact {
    interface PushPresent {
        fun pushStuDebug(author: String, requestBody: PushRequest)
    }

    interface PushView : BaseView
}