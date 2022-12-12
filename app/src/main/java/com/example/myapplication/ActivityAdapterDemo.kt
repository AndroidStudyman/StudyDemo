package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityAdapterDemoBinding

class ActivityAdapterDemo : AppCompatActivity() {

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
    }
}