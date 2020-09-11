package com.yunxiao.testpushtool.pushAPI

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yunxiao.testpushtool.R

/**
 * description
 * @author zhaiyaohua createBy 2020/9/10
 */
class PushOptionAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_option_list) {
    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.tvOption, item)
        helper.addOnClickListener(R.id.tvDelete)
    }
}