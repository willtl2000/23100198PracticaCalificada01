package dev.wticona.a23100198practicacalificada01.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.wticona.a23100198practicacalificada01.firebase.FirestoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConversionViewModel : ViewModel() {

    private val firestoreManager = FirestoreManager()

    private val _conversionResult = MutableStateFlow("")
    val conversionResult = _conversionResult.asStateFlow()

    fun performConversion(amount: String, fromCurrency: String, toCurrency: String) {
        viewModelScope.launch {
            val rates = firestoreManager.getRates()
            val amountToConvert = amount.toDoubleOrNull() ?: 0.0

            val fromRate = rates[fromCurrency] ?: 1.0
            val toRate = rates[toCurrency] ?: 1.0

            val result = amountToConvert * (toRate / fromRate)

            _conversionResult.value = "$amount $fromCurrency equivalen a ${String.format("%.2f", result)} $toCurrency"
        }
    }
}