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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
    fun appendInput(value: String) {// Añade el valor de los botones al input
        input += value
    }

    fun clearInput() {// Vacia el input
        input = ""
    }
}

@Composable
fun PortraitMusic(modifier: Modifier, viewModel: MusicViewModel) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            Row {
                Text(
                    text = "Another One Bites The Dust",
                    modifier = modifier
                )

            }

            Row {
                Text(
                    text = "Queen",
                    modifier = modifier
                )
            }
            Row {
                val imageModifier = Modifier
                    .size(50.dp)
                    .border(BorderStroke(1.dp, Color.Black))
                    .background(Color.Yellow)
                Image(
                    painter = painterResource(id = R.drawable.aobtd),
                    contentDescription = stringResource(id = R.string.imageDisc),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
            }
            Row {
                var sliderPosition by remember { mutableFloatStateOf(0f) }
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it }
                )
                Text(text = sliderPosition.toString())
            }
            Row {
                Button(onClick = { viewModel.clearInput() }) {
                    Text("f")
                }
            }
            Row {

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
