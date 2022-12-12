package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
    }
}