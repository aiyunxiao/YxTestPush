package com.yunxiao.testpushtool

import io.reactivex.disposables.Disposable

/**
 * description
 * @author zhaiyaohua createBy 2020/9/11
 */
interface BaseView {
    fun addDisposable(disposable: Disposable?)
}