package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewModel:MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        var bmi = viewModel.bmi

        val heightInput = findViewById<EditText>(R.id.heightInput)
        val weightInput = findViewById<EditText>(R.id.weightInput)
        val calcButton = findViewById<Button>(R.id.button)
        val bmiValue = findViewById<TextView>(R.id.bmiValue)
        val bmiText = findViewById<TextView>(R.id.bmiText)

//        bmiValue.text = String.format("%.1f",bmi.toDouble()).toString()

        calcButton.setOnClickListener{
            if(heightInput.text.toString() != "" && weightInput.text.toString() != "" ){
                var height = heightInput.text.toString().toDouble()
                var weight = weightInput.text.toString().toDouble()
                var statusText = ""

//                Log.i("Height",height.toString())
//                Log.i("weight",weight.toString())

//                var bmi = (((weight/height)/height)*10000)
                var bmi = viewModel.updateBmi(height,weight)
                bmi = bmi.toDouble()
                when{
                    bmi < 18.5 -> statusText="Underweight"
                    bmi in 18.5..24.9 -> statusText="Healthy"
                    bmi in 25.0..29.9 -> statusText="Overweight"
                    bmi >= 30.0 -> statusText="Obese"

                }

                bmiValue.text = String.format("%.1f",bmi).toDouble().toString()
                bmiText.text  = statusText.toString()
            }
            else{
                val snackbar = Snackbar.make(it, "Please enter valid height and weight",Snackbar.LENGTH_SHORT)
                snackbar.show()
            }

        }


    }
}