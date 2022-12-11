package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.compose.ui.text.toUpperCase
import com.example.myapplication.databinding.ActivitySharedPreferencesDemoBinding
import java.util.*

class SharedPreferencesDemo : AppCompatActivity() {
    lateinit var binding : ActivitySharedPreferencesDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferencesDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveButton.setOnClickListener {
            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            editor.putString("name", "Tom")
            editor.putInt("age", 18)
            editor.putBoolean("married", false)
            editor.apply()
        }
        val edit = getPreferences(Context.MODE_PRIVATE).edit()
        edit.putLong("id", 5555555)
        edit.apply()

        binding.restoreButton.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Log.d("Main", "name is $name, age is $age, married is $married")
        }

        val list = listOf("Apple", "Banana", "Pear")
        val newList = list.filter { it.length <= 5 }
            .map { it.uppercase(Locale.getDefault()) }
        for (fruit in newList) {
            println(fruit)
        }
        val anyResult = list.any { it.length <= 5 }
        val allResult = list.all { it.length <= 5 }
        println("anyResult is $anyResult, allResult is $allResult")

        val maxLengthFruit = list.maxBy { it.length }
        println("max length fruit is $maxLengthFruit")
    }
    val thread = Thread {
        println("Thread is running")
    }.start()
}