package com.example.myapplication

class ModelDemoItem(
    var name : String? = null,
    var age: Int) {
    fun getItemName() : String? {
        return name
    }

    fun getItemAge() : Int {
        return age
    }
}