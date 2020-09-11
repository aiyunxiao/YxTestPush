package com.yunxiao.testpushtool


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.yunxiao.testpushtool.pushAPI.PushOptionAdapter
import com.yunxiao.testpushtool.pushAPI.PushRequest
import com.yunxiao.testpushtool.pushAPI.PushToolConfig
import kotlinx.android.synthetic.main.activity_send_push.*

/**
 * description
 *
 * @author createBy 2020/9/9
 */
class SendPushActivity : BaseTestPushActivity(), PushContact.PushView {
    lateinit var pushPresenter: PushContact.PushPresent
    lateinit var registrationIdAdapter: PushOptionAdapter
    lateinit var aliseAdapter: PushOptionAdapter
    lateinit var pushContent: PushRequest
    var deletedListener: (PushOptionAdapter, View, Int) -> Unit = { adapter, view, position ->
        if (view.id == R.id.tvDelete) {
            adapter.data.removeAt(position)
            adapter.notifyDataSetChanged()
        }
    }


    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_push)
        initView()
    }

    private fun initView() {
        tvBack.setOnClickListener { finish() }
        PushToolConfig.setResponseListener { resultStr ->
            this.runOnUiThread {
                if (resultStr.isNotEmpty()) {
                    tvResult.text = PushGsonUtill.getJsonStr(resultStr)
                }
            }
        }
        pushContent =
            PushGsonUtill.fromJson(PushToolConfig.getPushContent(this), PushRequest::class.java)

        pushPresenter = Presenter(this)
        tvEditPushData.setOnClickListener {
            EditPushDataDialog(this) { content, dialog ->
                if (PushGsonUtill.validate(content)) {
                    pushContent.notification.android.extras = content
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, "输入json格式错误", Toast.LENGTH_SHORT).show()
                }
            }.apply {
                setContent(PushGsonUtill.getJsonStr(pushContent.notification.android.extras))
            }.show()
        }
        btnPush.setOnClickListener {
            if (NotificationsCheckUtil.areNotificationsEnabled(this.applicationContext)) {
                pushPresenter.pushStuDebug(
                    if (cbRelease.isChecked) PushToolConfig.getReleaseAuth(this) else PushToolConfig.getTestAuth(
                        this
                    ), pushContent
                )
            } else {
                Toast.makeText(this, "同学通知权限没开", Toast.LENGTH_SHORT).show()

            }
        }
        registrationRv.apply {
            layoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = PushOptionAdapter().also {
                registrationIdAdapter = it
                it.setOnItemChildClickListener { adapter, view, position ->
                    deletedListener(adapter as PushOptionAdapter, view, position)
                }

            }
        }
        rvAlias.apply {
            layoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = PushOptionAdapter().also {
                aliseAdapter = it
                it.setOnItemChildClickListener { adapter, view, position ->
                    deletedListener(adapter as PushOptionAdapter, view, position)
                }

            }
        }
        tvAddRegistrationId.setOnClickListener {
            EditPushDataDialog(this) { content, dialog ->
                registrationIdAdapter.run {
                    pushContent.audience.registrationId.add(content)
                    notifyDataSetChanged()
                }
                dialog.dismiss()
            }.show()
        }

        tvAddAlias.setOnClickListener {
            EditPushDataDialog(this) { content, dialog ->
                aliseAdapter.run {
                    pushContent.audience.alias.add(content)
                    notifyDataSetChanged()
                }
                dialog.dismiss()
            }.show()
        }
        tvClearAddRegistrationId.setOnClickListener {
            pushContent.audience.registrationId.clear()
            registrationIdAdapter.notifyDataSetChanged()
        }
        tvCleartvAddAlias.setOnClickListener {
            pushContent.audience.alias.clear()
            aliseAdapter.notifyDataSetChanged()
        }
        registrationIdAdapter.setNewData(pushContent.audience.registrationId)
        aliseAdapter.setNewData(pushContent.audience.alias)
    }

    override fun onPause() {
        super.onPause()
        PushToolConfig.savePushContent(this, PushGsonUtill.toJson(pushContent))
    }
}