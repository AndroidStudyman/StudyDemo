package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.myapplication.databinding.ActivityAdapterDemoBinding
import javax.net.ssl.SSLEngineResult.Status

class AdapterDemo : BaseQuickAdapter<ModelDemoItem, AdapterDemo.VH>() {

    lateinit var binding: ActivityAdapterDemoBinding

    //自定义ViewHolder类
    class VH(
        parent: ViewGroup,
        binding: ActivityAdapterDemoBinding = ActivityAdapterDemoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        // 返回一个 ViewHolder
        return VH(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int, item: ModelDemoItem?) {
        // 设置item数据
        item?.getItemName()
    }
}
