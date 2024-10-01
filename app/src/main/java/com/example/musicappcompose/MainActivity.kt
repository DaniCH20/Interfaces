package com.example.musicappcompose

import android.content.Context
import android.content.res.Configuration
import android.media.MediaPlayer
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicappcompose.ui.theme.MusicAppComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    var input by mutableStateOf("")
    var mediaPlayer: MediaPlayer? = null
    var isPlaying by mutableStateOf(false)
    var currentPosition by mutableStateOf(0f)

    fun clearInput() {
        input = ""
    }

    fun setVolume(volume: Float) {
        mediaPlayer?.setVolume(volume, volume)
    }

    fun MusicStart(context: Context) {
        if (isPlaying) {//Si la musica se esta reproduciendo la pausa
            mediaPlayer?.pause()
        } else {
            if (mediaPlayer == null) {//Si no esta creado abre el archivo mp3 ubicado en la carpeta raw
                mediaPlayer = MediaPlayer.create(context, R.raw.anotherobtd)
                mediaPlayer?.setOnCompletionListener {
                    isPlaying = false
                    currentPosition = 0f
                }
            }
            mediaPlayer?.start()//inicia la cancion
        }
        isPlaying = !isPlaying
        startPositionUpdater()
    }

    private fun startPositionUpdater() {
        viewModelScope.launch {
            while (isPlaying) {
                currentPosition = mediaPlayer?.currentPosition?.toFloat() ?: 0f
                delay(1000) // Actualiza cada segundo
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

// Función para formatear el tiempo en minutos y segundos
fun formatTime(milliseconds: Int): String {
    val seconds = (milliseconds / 1000) % 60
    val minutes = (milliseconds / (1000 * 60)) % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Composable
fun PortraitMusic(modifier: Modifier, viewModel: MusicViewModel) {
    val mContext = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray) // Fondo de la pantalla
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically // Alineación vertical en el centro
            ) {
                Text(
                    text = "                      Another One Bites The Dust",
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 16.dp) // Espaciado inferior
                )
                Button(
                    onClick = { viewModel.clearInput() },
                    modifier = Modifier.size(70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lista),
                        contentDescription = "Lista",
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
                    modifier = Modifier
                        .padding(bottom = 16.dp) // Espaciado inferior
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
                        .border(BorderStroke(10.dp, Color.Black))
                        .padding(4.dp) // Espaciado interno de la imagen
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                var sliderVolumen by remember { mutableFloatStateOf(0.7f) } // 70% por defecto
                Slider(
                    value = sliderVolumen,
                    onValueChange = {
                        sliderVolumen = it
                        viewModel.setVolume(it) // Ajustar el volumen
                    },
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
            var sliderPosition by remember { mutableFloatStateOf(viewModel.currentPosition) }
            Slider(
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                    // Puedes ajustar la posición actual del MediaPlayer aquí si lo deseas
                    viewModel.mediaPlayer?.seekTo((it * viewModel.mediaPlayer?.duration!!).toInt())
                },
                modifier = Modifier.padding(vertical = 16.dp)
            )
            // Calcular tiempo restante
            val totalDuration = viewModel.mediaPlayer?.duration ?: 0
            val timeElapsed = viewModel.currentPosition.toInt()
            val timeRemaining = totalDuration - timeElapsed
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Inicio: ${formatTime(timeElapsed)}")
                Text(text = "Fin: ${formatTime(timeRemaining)}")// Mostrar valor del slider
            }
            // Actualiza la posición del slider mientras la música se reproduce
            LaunchedEffect(viewModel.isPlaying) {
                while (viewModel.isPlaying) {
                    sliderPosition =
                        viewModel.currentPosition / (viewModel.mediaPlayer?.duration ?: 1).toFloat()
                    delay(1000) // Actualiza cada segundo
                }
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
                        painter = painterResource(id = R.drawable.previous),
                        contentDescription = "anterior",
                        contentScale = ContentScale.Fit
                    )
                }
                Button(
                    onClick = {
                        viewModel.MusicStart(mContext)
                    },
                    modifier = Modifier.size(70.dp)
                ) {
                    val playPauseIcon = if (viewModel.isPlaying) {
                        R.drawable.pause // Cambia por tu recurso de imagen de pausa
                    } else {
                        R.drawable.play // Cambia por tu recurso de imagen de play
                    }
                    Image(
                        painter = painterResource(id = playPauseIcon),
                        contentDescription = if (viewModel.isPlaying) "Pause" else "Play",
                        contentScale = ContentScale.Fit
                    )
                }
                Button(
                    onClick = { viewModel.clearInput() },
                    modifier = Modifier.size(70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.next),
                        contentDescription = "siguiente",
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}

@Composable
fun LandscapeMusic(modifier: Modifier, viewModel: MusicViewModel) {
    val mContext = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray) // Fondo de la pantalla
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp, end = 40.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically // Alineación vertical en el centro
            ) {
                Text(
                    text = "Another One Bites The Dust",
                    modifier = Modifier
                        .weight(1f) // Ocupa el espacio restante
                        .padding(bottom = 16.dp) // Espaciado inferior
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Queen",
                        modifier = Modifier.padding(bottom = 16.dp) // Espaciado inferior
                    )
                    var sliderVolumen by remember { mutableFloatStateOf(0.7f) } // 70% por defecto
                    Slider(
                        value = sliderVolumen,
                        onValueChange = {
                            sliderVolumen = it
                            viewModel.setVolume(it) // Ajustar el volumen
                        },
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .width(250.dp) // Ajusta el ancho
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.aobtd),
                    contentDescription = stringResource(id = R.string.imageDisc),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp) // Aumentar tamaño de la imagen
                        .border(BorderStroke(5.dp, Color.Black))
                        .padding(4.dp) // Espaciado interno de la imagen
                )
                Column(modifier = Modifier) {
                    Button(
                        onClick = { viewModel.clearInput() },
                        modifier = Modifier.size(70.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.lista),
                            contentDescription = "Lista",
                            contentScale = ContentScale.Fit
                        )
                    }
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
            }
            var sliderPosition by remember { mutableFloatStateOf(viewModel.currentPosition) }
            // Calcular tiempo restante
            val totalDuration = viewModel.mediaPlayer?.duration ?: 0
            val timeElapsed = viewModel.currentPosition.toInt()
            val timeRemaining = totalDuration - timeElapsed
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Inicio: ${formatTime(timeElapsed)}")
                Slider(
                    value = sliderPosition,
                    onValueChange = {
                        sliderPosition = it
                        // Puedes ajustar la posición actual del MediaPlayer aquí si lo deseas
                        viewModel.mediaPlayer?.seekTo((it * viewModel.mediaPlayer?.duration!!).toInt())
                    },
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .width(500.dp)
                )
                // Actualiza la posición del slider mientras la música se reproduce
                LaunchedEffect(viewModel.isPlaying) {
                    while (viewModel.isPlaying) {
                        sliderPosition =
                            viewModel.currentPosition / (viewModel.mediaPlayer?.duration
                                ?: 1).toFloat()
                        delay(1000) // Actualiza cada segundo
                    }
                }
                Text(text = "Fin: ${formatTime(timeRemaining)}")// Mostrar valor del slider
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
                        painter = painterResource(id = R.drawable.previous),
                        contentDescription = "anterior",
                        contentScale = ContentScale.Fit
                    )
                }
                Button(
                    onClick = {
                        viewModel.MusicStart(mContext)
                    },
                    modifier = Modifier.size(70.dp)
                ) {
                    val playPauseIcon =
                        if (viewModel.isPlaying) {//Si se esta reproduciendo la cancion
                            R.drawable.pause // Cambia la imagen
                        } else {
                            R.drawable.play
                        }
                    Image(
                        painter = painterResource(id = playPauseIcon),
                        contentDescription = if (viewModel.isPlaying) "pause" else "play",
                        contentScale = ContentScale.Fit
                    )
                }
                Button(
                    onClick = { viewModel.clearInput() },
                    modifier = Modifier.size(70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.next),
                        contentDescription = "siguiente",
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
    } else {
        LandscapeMusic(modifier, viewModel)
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

@Preview(
    showBackground = true, showSystemUi = true,
    device = "spec:width=448dp,height=998dp,orientation=landscape"
)
@Composable
fun GreetinPreview() {
    MusicAppComposeTheme {
        val viewModel = MusicViewModel().apply { input = "123" }
        LandscapeMusic(Modifier.fillMaxSize(), viewModel)
    }
}
