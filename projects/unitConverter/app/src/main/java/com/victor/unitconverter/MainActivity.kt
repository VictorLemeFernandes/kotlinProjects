@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.victor.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victor.unitconverter.ui.theme.Roboto
import com.victor.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverterAppLayout()
                }
            }
        }
    }
}

@Composable
fun UnitConverterAppLayout() {
    var unitInput by remember { mutableStateOf("") }
    var results by remember {
        mutableStateOf(
            mutableMapOf<String, Double>().apply {
                listOf("miles", "yards", "kilometers", "centimeters", "feet", "milimeters", "inches", "meters").forEach { key -> this[key] = 0.0 }
            }
        )
    }
    val unitsSlider = listOf("centimeters", "meters", "kilometers", "inches", "miles")
    val sliderPosition = remember { mutableStateOf(0f) }
    val units = mapOf(
        "meters" to "METRO",
        "inches" to "POLEGADA",
        "centimeters" to "CENTÍMETRO",
        "kilometers" to "QUILÔMETRO",
        "miles" to "MILHA"
    )

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_title),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Roboto
        )

        Spacer(modifier = Modifier.height(96.dp))

        Text(
            text = "Unidade selecionada: ${units[unitsSlider[sliderPosition.value.toInt()]]}",
            fontFamily = Roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        // Slider para selecionar a unidade
        Slider(
            value = sliderPosition.value,
            onValueChange = {
                sliderPosition.value = it
                listOf("miles", "yards", "kilometers", "centimeters", "feet", "milimeters", "inches", "meters").forEach { key -> results[key] = 0.0 }
                unitInput = ""
            },
            valueRange = 0f..(unitsSlider.size - 1).toFloat(),
            steps = unitsSlider.size - 2, // Adiciona "cliques" entre os valores
        )

        Spacer(modifier = Modifier.height(16.dp))

        UnitInputField(
            value = unitInput,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            onValueChange = { unitInput = it },
            label = stringResource(R.string.type_the_unit)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                results = calculateConversions(unitsSlider[sliderPosition.value.toInt()], unitInput.toDoubleOrNull() ?: 0.0)
            }
        ) {
            Text(
                text = stringResource(R.string.convert_button),
                fontSize = 20.sp
            )
        }

        // Fazer o card p/ amostra dos resultados
        ShowResults(unitSelected = unitsSlider[sliderPosition.value.toInt()], results = results)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitInputField(
    value: String,
    keyboardOptions: KeyboardOptions,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
    ) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(text = label) },
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun ShowResults(
    unitSelected: String, results: MutableMap<String, Double>
) {
    val centimetersOptions = listOf("meters", "inches", "milimeters", "feet")
    val metersOptions = listOf("centimeters", "milimeters", "kilometers", "inches", "feet", "yards")
    val kilometersOptions = listOf("meters", "miles", "yards", "feet")
    val inchesOptions = listOf("centimeters", "meters", "feet", "yards")
    val milesOptions = listOf("kilometers", "meters", "yards", "feet")
    
    Column (
        modifier = Modifier.padding(all = 32.dp)
            ) {
        when (unitSelected) {
            "centimeters" -> {
                centimetersOptions.forEach {
                    key -> TextResult(results = results, key = key)
                }
            }

            "meters" -> {
                metersOptions.forEach {
                    key -> TextResult(results = results, key = key)
                }
            }

            "kilometers" -> {
                kilometersOptions.forEach {
                    key -> TextResult(results = results, key = key)
                }
            }

            "inches" -> {
                inchesOptions.forEach {
                    key -> TextResult(results = results, key = key)
                }
            }

            "miles" -> {
                milesOptions.forEach {
                    key -> TextResult(results = results, key = key)
                }
            }
        }
    }
}

@Composable
fun TextResult(results: MutableMap<String, Double>, key: String) {
    val units = mapOf(
        "meters" to "Metro",
        "inches" to "Polegada",
        "milimeters" to "Milímetro",
        "feet" to "Pé",
        "centimeters" to "Centímetro",
        "kilometers" to "Quilômetro",
        "yards" to "Jarda",
        "miles" to "Milha"
    )

    Text(
        text = "${units[key]}: ${String.format("%.3f", results[key])}",
        fontFamily = Roboto,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    )
}

fun calculateConversions(unitSelected: String, inputValue: Double): MutableMap<String, Double> {
    val results = mutableMapOf<String, Double>()

    when (unitSelected) {
        "centimeters" -> {
            results["meters"] = inputValue/100
            results["inches"] = inputValue/2.54
            results["milimeters"] = inputValue*10
            results["feet"] = inputValue/30.48

            return results
        }

        "meters" -> {
            results["centimeters"] = inputValue*100
            results["milimeters"] = inputValue*1000
            results["kilometers"] = inputValue/1000
            results["inches"] = inputValue*39.37
            results["feet"] = inputValue*3.281
            results["yards"] = inputValue*1.094

            return results
        }

        "kilometers" -> {
            results["meters"] = inputValue*1000
            results["miles"] = inputValue/1.609
            results["yards"] = inputValue*1094
            results["feet"] = inputValue*3281

            return results
        }

        "inches" -> {
            results["centimeters"] = inputValue*2.54
            results["meters"] = inputValue/39.37
            results["feet"] = inputValue/12
            results["yards"] = inputValue/36

            return results
        }

        // "miles"
        else -> {
            results["kilometers"] = inputValue*1.609
            results["meters"] = inputValue*1609
            results["yards"] = inputValue*1760
            results["feet"] = inputValue*5280

            return results
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverterAppLayout()
    }
}