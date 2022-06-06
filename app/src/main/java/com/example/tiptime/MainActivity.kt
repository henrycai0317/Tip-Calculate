package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()

        val cost = stringInTextField.toDoubleOrNull()
        if(cost ==null || cost == 0.0){
            displayTip(0.0)
            return
        }
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent   -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        if(binding.roundUpSwitch.isChecked){
            tip = ceil(tip)
        }
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        //format diffrent contries currencies
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        //set formated currencies text
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}