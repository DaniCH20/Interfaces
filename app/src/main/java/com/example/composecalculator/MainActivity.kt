package com.example.composecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecalculator.ui.theme.ComposeCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {


    Column(modifier = modifier) {
        val inputState = remember { mutableStateOf("") }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Calculadora",
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            // Mostrar el input actual
            Text(
                text = inputState.value, // Accede al valor con .value
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.labelSmall // Estilo de texto
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
        ) {

        }
        Row(
            modifier = Modifier
                .padding(60.dp)
                .fillMaxWidth()
        ) {

        }
        Row(
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = { onClick(".", { inputState.value += it }) },

                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
                    .testTag("buton.")
            ) {
                Text(".")
            }
            Button(
                onClick = { onClick("DEL", { inputState.value += it }) },
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
                    .testTag("butonDEL")
            ) {
                Text("DEL")
            }
            Button(
                onClick = { onClick("AC", { inputState.value += it }) },
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
                    .testTag("butonAC")
            ) {
                Text("AC")
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = { onClick("1", { inputState.value += it }) },
                modifier = Modifier
                    .padding(10.dp)
                    .testTag("buton1")
            ) {
                Text("1")
            }
            Button(
                onClick = { onClick("2", { inputState.value += it }) },
                modifier = Modifier
                    .padding(10.dp)
                    .testTag("buton2")
            ) {
                Text("2")
            }
            Button(
                onClick = { onClick("3", { inputState.value += it }) },
                modifier = Modifier
                    .padding(10.dp)
                    .testTag("buton3")
            ) {
                Text("3")
            }
            Button(
                onClick = { onClick("-", { inputState.value += it }) },
                modifier = Modifier
                    .padding(3.dp)
                    .weight(1f)
                    .testTag("buton-")
            ) {
                Text("-")
            }
            Button(
                onClick = { onClick("+", { inputState.value += it }) },
                modifier = Modifier
                    .padding(3.dp)
                    .weight(1f)
                    .testTag("buton+")

            ) {
                Text("+")
            }


        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()

        ) {
            Button(
                onClick = { onClick("4", { inputState.value += it }) },
                modifier = Modifier
                    .padding(10.dp)
                    .testTag("buton4")
            ) {
                Text("4")
            }
            Button(
                onClick = { onClick("5", { inputState.value += it }) },
                modifier = Modifier
                    .padding(10.dp)
                    .testTag("buton5")
            ) {
                Text("5")
            }
            Button(
                onClick = { onClick("6", { inputState.value += it }) },
                modifier = Modifier
                    .padding(10.dp)
                    .testTag("buton6")
            ) {
                Text("6")
            }
            Button(
                onClick = { onClick("x", { inputState.value += it }) },
                modifier = Modifier
                    .padding(3.dp)
                    .weight(1f)
                    .testTag("butonx")
            ) {
                Text("X")
            }
            Button(
                onClick = { onClick("/", { inputState.value += it }) },
                modifier = Modifier
                    .padding(3.dp)
                    .weight(1f)
                    .testTag("buton/")
            ) {
                Text("/")
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = { onClick("7", { inputState.value += it }) },
                modifier = Modifier
                    .padding(10.dp)
                    .testTag("buton7")
            ) {
                Text("7")
            }
            Button(
                onClick = { onClick("8", { inputState.value += it }) },
                modifier = Modifier
                    .padding(10.dp)
                    .testTag("buton8")
            ) {
                Text("8")
            }
            Button(
                onClick = { onClick("9", { inputState.value += it }) },
                modifier = Modifier
                    .padding(10.dp)
                    .testTag("buton9")
            ) {
                Text("9")
            }
            Button(
                onClick = { onClick("=", { inputState.value += it }) },
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
                    .testTag("buton=")
            ) {
                Text("=")
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = { onClick("0", { inputState.value += it }) },
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
                    .testTag("buton0")
            ) {
                Text("0")
            }

        }


    }


}

fun onClick(value: String, onInputChange: (String) -> Unit) {
    onInputChange(value) // Agrega el valor al input
}


