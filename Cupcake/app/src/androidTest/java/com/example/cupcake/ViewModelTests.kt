package com.example.cupcake

import com.example.cupcake.model.OrderViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ViewModelTests {

    @Test
    fun quantity_twelve_cupcakes() {
        val viewModel = OrderViewModel()
        viewModel.ccOrderQuantity.observeForever {}
        viewModel.setQuantity(12)
        assertEquals(12, viewModel.ccOrderQuantity.value)
    }
}