package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {
    private var _ccOrderQuantity = MutableLiveData<Int>()
    val ccOrderQuantity : LiveData<Int> = _ccOrderQuantity

    private var _ccFlavor = MutableLiveData<String>()
    val ccFlavor : LiveData<String> = _ccFlavor

    private var _ccPickupDate = MutableLiveData<String>()
    val ccPickupDate : LiveData<String> = _ccPickupDate

    private var _ccTotalPrice = MutableLiveData<Double>()
    val ccTotalPrice : LiveData<String> = Transformations.map(_ccTotalPrice){
        NumberFormat.getCurrencyInstance().format(it)
    }

    val dateOptions = getPickupOptions()

    init {
        resetOrder()
    }

    fun setQuantity(numberCupcakes: Int)
    {
        _ccOrderQuantity.value = numberCupcakes
        updatePrice()
    }

    fun setFlavor(cupcakeFlavor: String)
    {
        _ccFlavor.value = cupcakeFlavor
    }

    fun setDate(pickupDate: String)
    {
        _ccPickupDate.value = pickupDate
        updatePrice()
    }

    fun hasNoFlavorSet(): Boolean {
        return _ccFlavor.value.isNullOrEmpty()
    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    fun resetOrder() {
        _ccOrderQuantity.value = 0
        _ccFlavor.value = ""
        _ccPickupDate.value = dateOptions[0]
        _ccTotalPrice.value = 0.0
    }

    private fun updatePrice() {
        var calculatedPrice = (_ccOrderQuantity.value ?: 0) * PRICE_PER_CUPCAKE
        if (dateOptions[0] == _ccPickupDate.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _ccTotalPrice.value = calculatedPrice
    }
}