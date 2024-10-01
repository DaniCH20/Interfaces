package com.example.xmlmusic

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var playButton: ImageButton
    private lateinit var txtViewInicio: TextView
    private lateinit var txtViewFin: TextView
    private lateinit var volumeSeekBar: SeekBar
    private val handler = Handler()
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton = findViewById(R.id.imageButton1)
        seekBar = findViewById(R.id.seekBar) // SeekBar para el tiempo
        txtViewInicio = findViewById(R.id.textView3)
        txtViewFin = findViewById(R.id.textView4)
        volumeSeekBar = findViewById(R.id.seekBar2) // SeekBar para el volumen

        mediaPlayer = MediaPlayer.create(this, R.raw.anotherobtd)

        // Configura el SeekBar de tiempo
        seekBar.max = mediaPlayer.duration

        // Configura el SeekBar de volumen
        audioManager = getSystemService(AudioManager::class.java)
        volumeSeekBar.max = 100
        volumeSeekBar.progress = 70 // Configura el volumen al 70% del seekbar

        // Establece el volumen inicial
        setVolume(0.7f) // 0.7 para 70%

        // Listener para el SeekBar de volumen
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val volume = progress / 100f
                setVolume(volume)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Configura el botón de reproducir
        playButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {//Si esta funcionando lo para y cambia la imagen del boton
                mediaPlayer.pause()
                playButton.setImageResource(R.drawable.play)
            } else {//Si no, inicia la cancion y cambia la imagen del boton
                mediaPlayer.start()
                playButton.setImageResource(R.drawable.pause)
                updateSeekBar() // Inicia la actualización del SeekBar
            }
        }

        // Listener para el SeekBar de tiempo
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress) // Cambia la posición de reproducción
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (!mediaPlayer.isPlaying) {
                    mediaPlayer.start()
                }
            }
        })

        mediaPlayer.setOnCompletionListener {
            playButton.setImageResource(R.drawable.play)
            seekBar.progress = 0 // Resetea el SeekBar
            txtViewInicio.text = formatTime(0) // Resetea el tiempo
            txtViewFin.text = formatTime(mediaPlayer.duration) // Resetea el tiempo restante
        }
    }

    private fun updateSeekBar() {
        if (mediaPlayer.isPlaying) {
            seekBar.progress = mediaPlayer.currentPosition
            txtViewInicio.text = formatTime(mediaPlayer.currentPosition) // Actualiza tiempo
            val remainingTime = mediaPlayer.duration - mediaPlayer.currentPosition
            txtViewFin.text = formatTime(remainingTime) // Actualiza tiempo restante
            handler.postDelayed({ updateSeekBar() }, 1000) // Actualiza cada segundo
        }
    }

    private fun formatTime(ms: Int): String {
        val totalSeconds = ms / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d", minutes, seconds) // Formato MM:SS
    }

    // Establece el volumen para ambos canales
    private fun setVolume(volume: Float) {
        mediaPlayer.setVolume(volume, volume)
    }

    // Destruye la actividad y libera recursos del dispositivo
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
