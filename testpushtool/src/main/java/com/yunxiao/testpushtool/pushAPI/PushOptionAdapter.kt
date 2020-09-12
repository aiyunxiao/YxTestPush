package com.yunxiao.testpushtool.pushAPI

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yunxiao.testpushtool.R

/**
 * description
 * @author zhaiyaohua createBy 2020/9/10
 */
class PushOptionAdapter(var context: Context) :
    RecyclerView.Adapter<PushOptionAdapter.OptionViewHolder>() {
    lateinit var data: ArrayList<String>
    fun setNewData(data: ArrayList<String>?) {
        this.data = data ?: arrayListOf()
    }


    class OptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvOption: TextView = view.findViewById(R.id.tvOption)
        var tvDelete: TextView = view.findViewById(R.id.tvDelete)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): OptionViewHolder {
        return OptionViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_option_list, p0,false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: OptionViewHolder, p1: Int) {
        var item = data[p1]
        holder.run {
            tvOption.text = item
            tvDelete.setOnClickListener { listener(this@PushOptionAdapter, tvDelete, p1) }
        }
    }

    private var listener: (PushOptionAdapter, View, Int) -> Unit = { _, _, _-> }
    fun setOnItemChildClickListener(listener: (PushOptionAdapter, View, Int) -> Unit) {
        this.listener = listener
    }

}