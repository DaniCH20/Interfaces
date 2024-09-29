package com.example.composecalculator

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}

val CustomColorScheme = lightColorScheme(
    primary = Color(0xFFD7DBDD), // Plomo
    onPrimary = Color.Black,
    secondary = Color(0xFFD4441A), // Naranja
    onSecondary = Color.White,
    background = Color(0xFF99A3A4), // Gris claro
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
)

class CalculatorViewModel : ViewModel() {
    var input: String = ""
}

@Composable
fun ComposeCalculatorTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CustomColorScheme,
        typography = Typography(),
        content = content
    )
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
        // Diseño para modo vertical
        PortraitCalculator(modifier, viewModel)
    } else {
        // Diseño para modo horizontal
        LandscapeCalculator(modifier, viewModel)
    }
}

@Composable
fun PortraitCalculator(modifier: Modifier, viewModel: CalculatorViewModel) {

    // Un diseño diferente para el modo horizontal
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        val inputState = remember { mutableStateOf("") }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    "Calculadora",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surface
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                // Mostrar el input actual
                Text(
                    text = inputState.value,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.surface
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(200.dp)
                    .fillMaxWidth()
            ) {

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Button(
                    onClick = { onClick(".", { inputState.value += it }) },

                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp)
                        .testTag("buton."),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background // Cambia el color aquí
                    )
                ) {
                    Text(".", color = MaterialTheme.colorScheme.onBackground)
                }
                Button(
                    onClick = { onClick("DEL", { inputState.value += it }) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp)
                        .testTag("butonDEL"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary // Cambia el color aquí
                    )
                ) {
                    Text("DEL", color = MaterialTheme.colorScheme.onSecondary)
                }
                Button(
                    onClick = { onClick("AC", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)
                        .testTag("butonAC"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary // Cambia el color aquí
                    )
                ) {
                    Text("AC", color = MaterialTheme.colorScheme.onSecondary)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Button(
                    onClick = { onClick("1", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(10.dp)
                        .testTag("buton1"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("1", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("2", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(10.dp)
                        .testTag("buton2"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("2", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("3", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(10.dp)
                        .testTag("buton3"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("3", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("-", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(3.dp)
                        .weight(1f)
                        .testTag("buton-"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("-", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("+", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(3.dp)
                        .weight(1f)
                        .testTag("buton+"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )

                ) {
                    Text("+", color = MaterialTheme.colorScheme.onPrimary)
                }


            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)

            ) {
                Button(
                    onClick = { onClick("4", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(10.dp)
                        .testTag("buton4"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("4", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("5", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(10.dp)
                        .testTag("buton5"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("5", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("6", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(10.dp)
                        .testTag("buton6"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("6", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("x", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(3.dp)
                        .weight(1f)
                        .testTag("butonx"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("X", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("/", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(3.dp)
                        .weight(1f)
                        .testTag("buton/"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("/", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            ) {
                Button(
                    onClick = { onClick("7", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(10.dp)
                        .testTag("buton7"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("7", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("8", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(10.dp)
                        .testTag("buton8"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("8", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("9", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(10.dp)
                        .testTag("buton9"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("9", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("=", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1f)
                        .testTag("buton="),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text("=", color = MaterialTheme.colorScheme.onBackground)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Button(
                    onClick = { onClick("0", { inputState.value += it }) },
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)
                        .testTag("buton0"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text("0", color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}

@Composable
fun LandscapeCalculator(modifier: Modifier, viewModel: CalculatorViewModel) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {

        val inputState = remember { mutableStateOf("") }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    "Calculadora",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surface
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                // Mostrar el input actual
                Text(
                    text = inputState.value,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.surface
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Button(
                    onClick = { onClick("%", { inputState.value += it }) },

                    modifier = Modifier
                        .size(100.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton."),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background // Cambia el color aquí
                    )
                ) {
                    Text("%", color = MaterialTheme.colorScheme.onBackground)
                }
                Button(
                    onClick = { onClick("DEL", { inputState.value += it }) },
                    modifier = Modifier
                        .size(100.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("butonDEL"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary // Cambia el color aquí
                    )
                ) {
                    Text("DEL", color = MaterialTheme.colorScheme.onSecondary)
                }
                Button(
                    onClick = { onClick("AC", { inputState.value += it }) },
                    modifier = Modifier
                        .size(100.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("butonAC"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary // Cambia el color aquí
                    )
                ) {
                    Text("AC", color = MaterialTheme.colorScheme.onSecondary)
                }
                Button(
                    onClick = { onClick("1", { inputState.value += it }) },

                    modifier = Modifier
                        .size(135.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton."),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary // Cambia el color aquí
                    )
                ) {
                    Text("1", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("2", { inputState.value += it }) },

                    modifier = Modifier
                        .size(135.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton."),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary // Cambia el color aquí
                    )
                ) {
                    Text("2", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("3", { inputState.value += it }) },

                    modifier = Modifier
                        .size(135.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton."),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary // Cambia el color aquí
                    )
                ) {
                    Text("3", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("+", { inputState.value += it }) },

                    modifier = Modifier
                        .size(80.dp)
                        .padding(10.dp)
                        .testTag("buton."),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background // Cambia el color aquí
                    )
                ) {
                    Text("+", color = MaterialTheme.colorScheme.onBackground)
                }
                Button(
                    onClick = { onClick("-", { inputState.value += it }) },

                    modifier = Modifier
                        .size(80.dp)
                        .padding(10.dp)
                        .testTag("buton."),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background // Cambia el color aquí
                    )
                ) {
                    Text("-", color = MaterialTheme.colorScheme.onBackground)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Button(
                    onClick = { onClick("(", { inputState.value += it }) },
                    modifier = Modifier
                        .size(100.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton1"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text("(", color = MaterialTheme.colorScheme.onBackground)
                }
                Button(
                    onClick = { onClick("²", { inputState.value += it }) },
                    modifier = Modifier
                        .size(100.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton2"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text("x²", color = MaterialTheme.colorScheme.onBackground)
                }
                Button(
                    onClick = { onClick("0", { inputState.value += it }) },
                    modifier = Modifier
                        .size(100.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton3"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text("0", color = MaterialTheme.colorScheme.onBackground)
                }

                Button(
                    onClick = { onClick("4", { inputState.value += it }) },
                    modifier = Modifier
                        .size(135.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton-"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("4", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("5", { inputState.value += it }) },
                    modifier = Modifier
                        .size(135.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton+"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )

                ) {
                    Text("5", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("6", { inputState.value += it }) },
                    modifier = Modifier

                        .size(135.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton+"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )

                ) {
                    Text("6", color = MaterialTheme.colorScheme.onPrimary)
                }

                Button(
                    onClick = { onClick("x", { inputState.value += it }) },

                    modifier = Modifier
                        .size(80.dp)
                        .padding(10.dp)
                        .testTag("buton."),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background // Cambia el color aquí
                    )
                ) {
                    Text("X", color = MaterialTheme.colorScheme.onBackground)
                }
                Button(
                    onClick = { onClick("/", { inputState.value += it }) },

                    modifier = Modifier
                        .size(80.dp)
                        .padding(10.dp)
                        .testTag("buton."),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background // Cambia el color aquí
                    )
                ) {
                    Text("/", color = MaterialTheme.colorScheme.onBackground)
                }

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)

            ) {
                Button(
                    onClick = { onClick(")", { inputState.value += it }) },
                    modifier = Modifier
                        .size(100.dp, 80.dp)
                        .padding(10.dp)
                        .testTag("buton4"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text(")", color = MaterialTheme.colorScheme.onBackground)
                }
                Button(
                    onClick = { onClick("√", { inputState.value += it }) },
                    modifier = Modifier
                        .size(100.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton5"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text("√", color = MaterialTheme.colorScheme.onBackground)
                }
                Button(
                    onClick = { onClick(".", { inputState.value += it }) },
                    modifier = Modifier
                        .size(100.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton6"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text(".", color = MaterialTheme.colorScheme.onBackground)
                }
                Button(
                    onClick = { onClick("7", { inputState.value += it }) },
                    modifier = Modifier
                        .size(135.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("butonx"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("7", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("8", { inputState.value += it }) },
                    modifier = Modifier
                        .size(135.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton/"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("8", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("9", { inputState.value += it }) },
                    modifier = Modifier
                        .size(135.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton/"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("9", color = MaterialTheme.colorScheme.onPrimary)
                }
                Button(
                    onClick = { onClick("=", { inputState.value += it }) },
                    modifier = Modifier
                        .size(170.dp, 70.dp)
                        .padding(10.dp)
                        .testTag("buton/"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("=", color = MaterialTheme.colorScheme.onPrimary)
                }
            }


        }

    }


}

fun onClick(value: String, onInputChange: (String) -> Unit) {
    onInputChange(value) // Agrega el valor al input
}


@Preview(
    showBackground = true, showSystemUi = true,
    device = "spec:width=448dp,height=998dp,orientation=landscape"
)
@Composable
fun LandscapeCalculatorPreview() {
    val viewModel = CalculatorViewModel().apply { input = "123" }
    LandscapeCalculator(Modifier.fillMaxSize(), viewModel)
}

@Preview(
    showBackground = true, showSystemUi = true,
    device = "spec:width=448dp,height=998dp,orientation=portrait"
)
@Composable
fun PortraiteCalculatorPreview() {
    val viewModel = CalculatorViewModel().apply { input = "123" }
    PortraitCalculator(Modifier.fillMaxSize(), viewModel)
}

