package com.example.cupcake

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cupcake.model.OrderViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ViewModelTests {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testquantity_twelve_cupcakes() {
        val viewModel = OrderViewModel()
        viewModel.ccOrderQuantity.observeForever {}
        viewModel.setQuantity(12)
        assertEquals(12, viewModel.ccOrderQuantity.value)
    }

    @Test
    fun testprice_twelve_cupcakes() {
        val viewModel = OrderViewModel()
        viewModel.setQuantity(12)
        viewModel.ccTotalPrice.observeForever {}
        assertEquals("$27.00", viewModel.ccTotalPrice.value)
    }
}