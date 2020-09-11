package com.yunxiao.testpushtool

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_edit_dialog.*


/**
 * description
 * @author zhaiyaohua createBy 2020/9/9
 */
class EditPushDataDialog : Dialog {
    constructor(context: Context, saveListener: (String,Dialog) -> Unit = {_,_->}) : super(
        context,
        R.style.pushDialog
    ) {
        setContentView(R.layout.view_edit_dialog)
        window?.run {
            setGravity(Gravity.CENTER)
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        btnSave.setOnClickListener {
            var str = etContent.text.toString().trim()
            if (str.isNotEmpty()) {
                saveListener(str,this)
            }
        }
        btnCancel.setOnClickListener {
            dismiss()
        }
    }
    fun setContent(content:String=""){
        etContent.setText(content)
    }
}