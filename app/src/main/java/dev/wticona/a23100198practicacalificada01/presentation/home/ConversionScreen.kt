package dev.wticona.a23100198practicacalificada01.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversionScreen(conversionViewModel: ConversionViewModel = viewModel()) {
    var amount by remember { mutableStateOf("") }
    var fromCurrency by remember { mutableStateOf("USD") }
    var toCurrency by remember { mutableStateOf("EUR") }
    val result by conversionViewModel.conversionResult.collectAsState()
    val currencies = listOf("USD", "EUR", "PEN", "GBP", "JPY")
    var expandedFrom by remember { mutableStateOf(false) }
    var expandedTo by remember { mutableStateOf(false) }


    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Monto") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        ExposedDropdownMenuBox(
            expanded = expandedFrom,
            onExpandedChange = { expandedFrom = !expandedFrom },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = fromCurrency,
                onValueChange = { },
                label = { Text("De") },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedFrom)
                },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedFrom,
                onDismissRequest = { expandedFrom = false }
            ) {
                currencies.forEach { currency ->
                    DropdownMenuItem(
                        text = { Text(currency) },
                        onClick = {
                            fromCurrency = currency
                            expandedFrom = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        ExposedDropdownMenuBox(
            expanded = expandedTo,
            onExpandedChange = { expandedTo = !expandedTo },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = toCurrency,
                onValueChange = { },
                label = { Text("A") },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTo)
                },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedTo,
                onDismissRequest = { expandedTo = false }
            ) {
                currencies.forEach { currency ->
                    DropdownMenuItem(
                        text = { Text(currency) },
                        onClick = {
                            toCurrency = currency
                            expandedTo = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { conversionViewModel.performConversion(amount, fromCurrency, toCurrency) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convertir")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (result.isNotBlank()) {
            Text(text = result)
        }
    }
}