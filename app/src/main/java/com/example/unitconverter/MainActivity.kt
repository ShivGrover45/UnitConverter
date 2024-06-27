package com.example.unitconverter

import android.graphics.Paint.Style
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                UnitConverterTheme {
                    Surface(modifier=Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background){
                    UnitConverter()
                }
            }

            }
        }
    }



@Composable
fun UnitConverter(){

   var inputValue by remember {
       mutableStateOf("")
   }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("Meter")
    }
    var outputUnit by remember {
        mutableStateOf("Meter")
    }
    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }
    val conFactor =remember {
        mutableStateOf(1.00)
    }
    val oConFactor= remember {
        mutableStateOf(1.00)
    }

    fun convertUnit(){
        val inputDoubleValue=inputValue.toDoubleOrNull()?:0.0
        val result=(inputDoubleValue*conFactor.value*100.0/oConFactor.value).roundToInt()/100.0
        outputValue=result.toString()
    }

    //Here all the UI elements will be placed in below each other
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter",
        style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue=it
        })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box(modifier = Modifier) {
                Button(onClick = {
                    iExpanded = true
                }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        iExpanded = false
                        inputUnit = "Meter"
                        conFactor.value = 1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        iExpanded = false
                        inputUnit = "Centimeter"
                        conFactor.value = 0.01
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        conFactor.value = 0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "millimeter") }, onClick = {
                        iExpanded = false
                        inputUnit = "millimeter"
                        conFactor.value = 0.001
                        convertUnit()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier) {
                Button(onClick = {
                    oExpanded = true
                }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        oExpanded = false
                        outputUnit = "Meter"
                        oConFactor.value = 1.00
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Centimetre") }, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeter"
                        oConFactor.value = 0.01
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        oConFactor.value = 0.3048
                        convertUnit()
                    })

                    DropdownMenuItem(text = { Text(text = "millimetre") }, onClick = {
                        oExpanded = false
                        outputUnit = "Millimeter"
                        oConFactor.value = 0.001
                        convertUnit()
                    }
                    )
                }
            }
        }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Result:$outputValue $outputUnit",
                style=MaterialTheme.typography.headlineSmall
                )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
