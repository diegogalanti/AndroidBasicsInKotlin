package com.gallardo.dado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { jogarDado() }
    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun jogarDado() {
        // Create new Dice object with 6 sides and roll it
        val dado = Dado(6)
        val jogada = dado.jogarDado()

        // Update the screen with the dice roll
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = jogada.toString()
    }
}