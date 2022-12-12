package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityAdapterDemoBinding

class ActivityAdapterDemo : AppCompatActivity() {

    lateinit var binding : ActivityAdapterDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdapterDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}