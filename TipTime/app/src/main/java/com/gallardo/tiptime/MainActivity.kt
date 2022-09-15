package com.gallardo.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gallardo.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            binding.tipResult.text = "0.0"
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        binding.tipResult.text = getString(R.string.tip_amount, NumberFormat.getCurrencyInstance().format(
            if (binding.roundUpSwitch.isChecked)
                ceil(cost * tipPercentage)
            else
                cost * tipPercentage))
    }
}