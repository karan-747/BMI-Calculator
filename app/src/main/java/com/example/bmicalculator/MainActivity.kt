package com.example.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tvBmi:TextView
    private lateinit var tvRemark:TextView
    private lateinit var tvNormalRange:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etHeight=findViewById<EditText>(R.id.etHeight)
        val etWeight=findViewById<EditText>(R.id.etWeight)
        tvBmi=findViewById(R.id.tvBMI)
        tvRemark=findViewById(R.id.tvRemark)
        tvNormalRange=findViewById(R.id.tvNormalRange)
        val calculate=findViewById<Button>(R.id.btnCalculate)

        calculate.setOnClickListener {
            val weight=etWeight.text.toString()
            val height=etHeight.text.toString()
            if(validateInput(height,weight)){
                val bmi=(weight.toFloat())/((height.toFloat())*(height.toFloat())/10000)
                val bmi2digits=String.format("%.2f",bmi).toFloat()
                displayBmi(bmi2digits)
            }
        }



    }
    private fun validateInput(Height:String?,Weight:String?):Boolean{
        return when {
            Weight.isNullOrEmpty() ->{
                Toast.makeText(this,"Please Enter Weight",Toast.LENGTH_SHORT).show()
                return false
            }
            Height.isNullOrEmpty() ->{
                Toast.makeText(this,"Please Enter Height",Toast.LENGTH_SHORT).show()
                return false
            }
            else ->{
                return true
            }

        }
    }
    private fun displayBmi(bmi:Float){
        var resultText=""
        var displayColor=0
        val normalText="Normal Range is 25.00 - 29.99"
        when {
            bmi<18.50 ->{
                resultText="Underweight"
                displayColor=R.color.Underweight
            }
            bmi in 18.51..24.99 ->{
                resultText="Healthy"
                displayColor=R.color.Healthy
            }
            bmi in 25.00 .. 29.99 ->{
                resultText="OverWeight"
                displayColor=R.color.OverWeight
            }
            else ->{
                resultText="Obese"
                displayColor=R.color.Obese
            }

        }
        tvBmi.text=bmi.toString()
        tvRemark.text=resultText
        tvRemark.setTextColor(ContextCompat.getColor(this,displayColor))
        tvNormalRange.text=normalText



    }
}
