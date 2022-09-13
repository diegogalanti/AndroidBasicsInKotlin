package com.gallardo.dado

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun generates_number() {
        val size = 6
        val dado = Dado(size)
        val resultado = dado.jogarDado()
        assertTrue("Number is not between 1 and $size", resultado in 1..size)
    }
}