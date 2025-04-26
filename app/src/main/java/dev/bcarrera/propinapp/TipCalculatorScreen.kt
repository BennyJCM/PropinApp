package dev.bcarrera.propinapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TipCalculatorScreen() {
    // Estado para el monto, porcentaje de la propina y el total calculado
    var amount by remember { mutableStateOf(TextFieldValue("")) }
    var tipPercentage by remember { mutableStateOf(0f) }
    var totalAmount by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título de la aplicación
        Text("Calculadora de Propina", style = MaterialTheme.typography.headlineMedium)

        // Campo para ingresar el monto de la cuenta
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Monto de cuenta (S/.)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Slider para elegir el porcentaje de la propina
        Text("Porcentaje de propina: ${tipPercentage.toInt()}%")
        Slider(
            value = tipPercentage,
            onValueChange = { tipPercentage = it },
            valueRange = 0f..30f,
            steps = 30, // Para tener 30 valores posibles
            modifier = Modifier.fillMaxWidth()
        )

        // Botón para calcular el total con propina
        Button(
            onClick = {
                val amountValue = amount.text.toFloatOrNull() ?: 0f
                val tip = amountValue * (tipPercentage / 100)
                totalAmount = amountValue + tip
            }
        ) {
            Text("Calcular")
        }

        // Mostrar el resultado con el total calculado
        if (totalAmount > 0) {
            Text("Monto con propina: S/. ${"%.2f".format(totalAmount)}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipCalculatorScreen()
}