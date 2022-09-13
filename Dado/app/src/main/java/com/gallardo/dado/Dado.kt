package com.gallardo.dado

class Dado(private val lados: Int) {
    fun jogarDado(): Int {
        return (1..lados).random()
    }
}