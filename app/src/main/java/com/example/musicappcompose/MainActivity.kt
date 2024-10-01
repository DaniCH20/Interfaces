package com.example.musicappcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MusicViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MusicScreen(modifier = Modifier.padding(innerPadding), viewModel)
            }
        }
    }
}

class MusicViewModel : ViewModel() {
    var input by mutableStateOf("") // Usar un estado mutable
    fun appendInput(value: String) { // Añade el valor de los botones al input
        input += value
    }

    fun clearInput() { // Vacia el input
        input = ""
    }
}

@Composable
fun PortraitMusic(modifier: Modifier, viewModel: MusicViewModel) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray) // Fondo de la pantalla
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp), // Agregar padding general
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically // Alineación vertical en el centro
            ) {
                Text(
                    text = "                      Another One Bites The Dust",
                    modifier = Modifier
                        .weight(1f) // Ocupa el espacio restante
                        .padding(bottom = 16.dp) // Espaciado inferior

                )

                Button(
                    onClick = { viewModel.clearInput() },
                    modifier = Modifier.size(70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lista),
                        contentDescription = "Compartir",
                        contentScale = ContentScale.Fit
                    )
                }
            }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                Text(
                    text = "Queen",
                    modifier = Modifier.padding(bottom = 16.dp) // Espaciado inferior
                        .align(Alignment.CenterVertically)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.aobtd),

                    contentDescription = stringResource(id = R.string.imageDisc),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(400.dp) // Aumentar tamaño de la imagen
                        .clip(CircleShape)
                        .border(BorderStroke(1.dp, Color.Black))
                        .padding(4.dp) // Espaciado interno de la imagen
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                var sliderVolumen by remember { mutableFloatStateOf(0f) }
                Slider(
                    value = sliderVolumen,
                    onValueChange = { sliderVolumen = it },
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .width(250.dp) // Ajusta el ancho
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { viewModel.clearInput() },
                    modifier = Modifier.size(70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.texto),
                        contentDescription = "Texto",
                        contentScale = ContentScale.Fit
                    )
                }
                Button(
                    onClick = { viewModel.clearInput() },
                    modifier = Modifier.size(70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.compartir),
                        contentDescription = "Compartir",
                        contentScale = ContentScale.Fit
                    )
                }
            }
            var sliderPosition by remember { mutableFloatStateOf(0f) }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Valor: ${sliderPosition.toFloat()}")
                Text(text = "Valor: ${sliderPosition.toFloat()}")// Mostrar valor del slider
            }
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { viewModel.clearInput() },
                    modifier = Modifier.size(70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.previous),
                        contentDescription = "Texto",
                        contentScale = ContentScale.Fit
                    )
                }
                Button(
                    onClick = { viewModel.clearInput() },
                    modifier = Modifier.size(70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.next),
                        contentDescription = "Compartir",
                        contentScale = ContentScale.Fit
                    )
                }
                Button(
                    onClick = { viewModel.clearInput() },
                    modifier = Modifier.size(70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.play),
                        contentDescription = "Compartir",
                        contentScale = ContentScale.Fit
                    )
                }
            }


        }
    }
}

@Composable
fun MusicScreen(modifier: Modifier = Modifier, viewModel: MusicViewModel) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
        PortraitMusic(modifier, viewModel)
    }
}

@Preview(
    showBackground = true, showSystemUi = true,
    device = "spec:width=448dp,height=998dp,orientation=portrait"
)
@Composable
fun GreetingPreview() {
    MusicAppComposeTheme {
        val viewModel = MusicViewModel().apply { input = "123" }
        PortraitMusic(Modifier.fillMaxSize(), viewModel)
    }
}
