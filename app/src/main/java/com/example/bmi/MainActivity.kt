package com.example.bmi

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editWeight = findViewById<EditText>(R.id.et_weight)
        val editHeight = findViewById<EditText>(R.id.et_height)
        val checkBtn = findViewById<Button>(R.id.bt_check)
        val answer = findViewById<TextView>(R.id.tv_resultsText)
        val intro = findViewById<TextView>(R.id.tv_intro)

        intro.text = "Check Your Body Mass Index below"


        var BMI: Double
        var userWeight: Int
        var userHeight: Int

        checkBtn.setOnClickListener {
            if (editWeight.text.isNotBlank() && editHeight.text.isNotBlank()){
                userWeight = editWeight.text.toString().toInt()
                userHeight = editHeight.text.toString().toInt()

                BMI = userWeight / (userHeight / 100.0).pow(2.0)

                val wState = weightType(BMI)

                answer.text = "With a BMI of $BMI, You are $wState."
                answer.visibility = VISIBLE

                editWeight.text.clear()
                editHeight.text.clear()

            } else {
                Toast.makeText(this,"Kindly fill all fields", Toast.LENGTH_SHORT).show()
                answer.visibility = INVISIBLE
            }
        }



    }
    private fun weightType(bmi:Double): String {
        var weightPhrase : String = ""

        when{
            (bmi < 18.5) -> weightPhrase = "Underweight"
            (bmi in 18.5..24.9) -> weightPhrase = "Normal"
            (bmi in 25.0..29.9) -> weightPhrase = "Overweight"
            (bmi in 30.0..34.9)-> weightPhrase = "Obese"
            (bmi in 35.0..39.9) -> weightPhrase = "Severely Obese"
            (bmi > 40.0) -> weightPhrase = "Morbidly Obese"
        }
        return weightPhrase
    }
}
