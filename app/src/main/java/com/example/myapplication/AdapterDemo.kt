package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.myapplication.databinding.LayoutPageAdapterDemoBinding

class AdapterDemo : BaseQuickAdapter<ModelDemoItem, AdapterDemo.VH>() {

    //自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        binding: LayoutPageAdapterDemoBinding = LayoutPageAdapterDemoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int, item: ModelDemoItem?) {
        // 设置item数据
        val name = holder.itemView.findViewById<TextView>(R.id.vName)
        if (item != null) {
            name.text = item.name
        }
        val age = holder.itemView.findViewById<TextView>(R.id.vAge)
        if (item != null) {
            age.text = item.age.toString()
        }
    }

}
