package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.myapplication.databinding.ActivityAdapterDemoBinding
import es.dmoral.toasty.Toasty

class ActivityAdapterDemo : AppCompatActivity() {

    val tag : String = "ActivityAdapterDemo"
    private lateinit var binding : ActivityAdapterDemoBinding
    private lateinit var adapterDemo: AdapterDemo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdapterDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vAdapterList.layoutManager = LinearLayoutManager(this)
        val itemBeans: ArrayList<ModelDemoItem> = ArrayList()
        for (i in 0..49) {
            itemBeans.add(ModelDemoItem("Name $i", i))
        }
        adapterDemo = AdapterDemo()
        adapterDemo.submitList(itemBeans)
        binding.vAdapterList.adapter = adapterDemo

        //item 点击事件
        adapterDemo.setOnItemClickListener { adapter, view, position ->
            Log.i(tag, " setOnItemClickListener ${adapter.hashCode()}, ${view.tag}, $position ")
            val intent = Intent(this, WebViewTest ::class.java)
            intent.action = Intent.ACTION_VIEW
            startActivity(intent)
        }

        //item 长按事件
        adapterDemo.setOnItemLongClickListener { adapter, view, position ->
            Log.i(tag, " setOnItemLongClickListener ${adapter.hashCode()}, ${view.tag}, $position ")
            val intent = Intent(this, MainActivity ::class.java)
            intent.action = Intent.ACTION_VIEW
            true
        }

        //item 子控件点击事件
        // 需要传递控件 id
        adapterDemo.addOnItemChildClickListener(R.id.vName) { adapter, view, position ->
            Log.i(tag, " addOnItemChildClickListener ${adapter.hashCode()}, ${view.tag}, $position ")
            Toasty.info(
                baseContext,
                "addOnItemChildClickListener!").show()
        }

        //item 子控件长按事件
        // 需要传递控件 id
        adapterDemo.addOnItemChildLongClickListener(R.id.vAge) {  adapter, view, position ->
            Log.i(tag, " addOnItemChildLongClickListener ${adapter.hashCode()}, ${view.tag}, $position ")
            Toasty.info(
                baseContext,
                "addOnItemChildLongClickListener!!").show()
            true

        }

        val item = ModelDemoItem("Name 6", 6)
        //修改index为1处的数据
        adapterDemo[1] = item

        // 尾部新增数据
        adapterDemo.add(item)

        // 在指定位置添加一条新数据
        adapterDemo.add(1, item)

        //val itemBeans: ArrayList<ModelDemoItem> = ArrayList()
        // 添加数据集
        adapterDemo.addAll(itemBeans)

        // 指定位置添加数据集
        adapterDemo.addAll(1, itemBeans)

        // 删除数据
        adapterDemo.remove(item)

        // 删除指定位置数据
        adapterDemo.removeAt(1)

        // 交换两个位置的数据
        adapterDemo.swap(1, 3)

        // 如果返回 -1，表示不存在
        adapterDemo.getItemPosition(item)

        // 如果返回 null，表示没有数据
        adapterDemo.getItem(1)

        /**
         * BaseQuickAdapter.AnimationType.AlphaIn
         * BaseQuickAdapter.AnimationType.ScaleIn
         * BaseQuickAdapter.AnimationType.SlideInBottom
         * BaseQuickAdapter.AnimationType.SlideInLeft
         * BaseQuickAdapter.AnimationType.SlideInRight
         */
        adapterDemo.setItemAnimation(BaseQuickAdapter.AnimationType.AlphaIn)
    }
}