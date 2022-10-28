package com.example.bmicalculator

import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {

    var bmi = ""

    fun updateBmi(height:Double,weight:Double):Double{
         var newBmi = (((weight/height)/height)*10000)
         bmi = newBmi.toString()
         return newBmi
    }
}