package com.example.calculadoragorjeta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoragorjeta.ui.theme.CalculadoraGorjetaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraGorjetaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GetMain()
                }
            }
        }
    }
}

@Composable
fun GetMain(){
    val total = remember { mutableStateOf(TextFieldValue()) }
    val sliderValue = remember { mutableFloatStateOf(18.0f) }

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        GetTotalEntry(value = total)
        GetPorcentagemSeekbar(sliderValue = sliderValue)
        GetPorcentagemValue(sliderValue = sliderValue)
        GetEntryGorjeta(sliderValue = sliderValue, total = total)
        GetEntryTotal(sliderValue = sliderValue, total = total)
    }
}

@Composable
fun GetPorcentagemValue(sliderValue: MutableFloatState){
    Row(Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
        Row(Modifier.padding(start = 140.dp, end = 40.dp)){
            Text(text = "15%",
                textAlign = TextAlign.Left,
                fontSize = 24.sp)
            Text(text = "${sliderValue.floatValue.toInt()}%",
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right,
                fontSize = 24.sp)
        }
    }
}

@Composable
fun GetPorcentagemSeekbar(sliderValue: MutableFloatState){
    Slider(
        value = sliderValue.floatValue,
        onValueChange = { newValue ->
            sliderValue.floatValue = newValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        valueRange = 0f..30f
    )
}

@Composable
fun GetEntryGorjeta(sliderValue: MutableFloatState, total: MutableState<TextFieldValue>){
    Row(Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically)
    {
        Text(
            text = "Gorjeta: ",
            textAlign = TextAlign.Start,
            fontSize = 24.sp
        )

        val combinedModifier = Modifier
            .width(140.dp)
            .padding(20.dp)

        val gorjetaFixa = remember { mutableStateOf(TextFieldValue()) }
        if (total.value.text.toFloatOrNull() != null){
            val gorjetaFixaResult = total.value.text.toFloat() * 0.15
            gorjetaFixa.value = TextFieldValue(String.format("%.2f", gorjetaFixaResult))
        }

        OutlinedTextField(
            value = gorjetaFixa.value,
            onValueChange = { newValue ->
                gorjetaFixa.value = newValue
            },
            modifier = combinedModifier,
            enabled = false
        )

        val gorjetaRandom = remember { mutableStateOf(TextFieldValue()) }
        if (total.value.text.toFloatOrNull() != null){
            val gorjetaRandomResult =  total.value.text.toFloat() * (sliderValue.floatValue / 100)
            gorjetaRandom.value = TextFieldValue(String.format("%.2f", gorjetaRandomResult))
        }
        OutlinedTextField(
            value = gorjetaRandom.value,
            onValueChange = { newValue ->
                gorjetaRandom.value = newValue
            },
            modifier = combinedModifier,
            enabled = false,
        )
    }
}

@Composable
fun GetEntryTotal(sliderValue: MutableFloatState, total: MutableState<TextFieldValue>){
    Row(Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically)
    {
        Text(
            text = "Total:     ",
            textAlign = TextAlign.Start,
            fontSize = 24.sp
        )

        val combinedModifier = Modifier
            .width(140.dp)
            .padding(20.dp)

        val gorjetaFixa = remember { mutableStateOf(TextFieldValue()) }
        if (total.value.text.toFloatOrNull() != null){
            val gorjetaFixaResult = (total.value.text.toFloat() * 0.15) + total.value.text.toFloat()
            gorjetaFixa.value = TextFieldValue(String.format("%.2f", gorjetaFixaResult))
        }

        OutlinedTextField(
            value = gorjetaFixa.value,
            onValueChange = { newValue ->
                gorjetaFixa.value = newValue
            },
            modifier = combinedModifier,
            enabled = false
        )

        val gorjetaRandom = remember { mutableStateOf(TextFieldValue()) }
        if (total.value.text.toFloatOrNull() != null){
            val gorjetaRandomResult =  (total.value.text.toFloat() * (sliderValue.floatValue / 100)) + total.value.text.toFloat()
            gorjetaRandom.value = TextFieldValue(String.format("%.2f", gorjetaRandomResult))
        }
        OutlinedTextField(
            value = gorjetaRandom.value,
            onValueChange = { newValue ->
                gorjetaRandom.value = newValue
            },
            modifier = combinedModifier,
            enabled = false,
        )
    }
}



@Composable
fun GetTotalEntry(value: MutableState<TextFieldValue>){
    Text(
        text = "Total: ",
        Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = 24.sp
    )

    OutlinedTextField(
        value = value.value,
        onValueChange = { newValue ->
            value.value = newValue
        },
        modifier = Modifier.padding(10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculadoraGorjetaTheme {
        GetMain()
    }
}