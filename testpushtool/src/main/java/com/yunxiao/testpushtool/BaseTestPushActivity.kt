package com.yunxiao.testpushtool

import android.support.v4.app.FragmentActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * description
 * @author zhaiyaohua createBy 2020/9/9
 */
open class BaseTestPushActivity : FragmentActivity(), BaseView {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun addDisposable(disposable: Disposable?) {
        disposable?.run {
            compositeDisposable.add(disposable)
        }
    }

    open fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose()
    }
}