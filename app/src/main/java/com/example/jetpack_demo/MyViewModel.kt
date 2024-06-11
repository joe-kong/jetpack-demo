package com.example.jetpack_demo

import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel(){
    var count = 0
    fun add() {
        count++
    }
    fun sub() {
        count--
    }
}