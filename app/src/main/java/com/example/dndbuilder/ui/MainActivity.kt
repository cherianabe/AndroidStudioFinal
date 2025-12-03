package com.example.dndbuilder.ui

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dndbuilder.R
import androidx.activity.viewModels
import com.example.dndbuilder.viewmodel.DndViewModel
import com.example.dndbuilder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var b : ActivityMainBinding
    private lateinit var mp: MediaPlayer

    val dndViewModel: DndViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(b.fragmentContainer.id, ChooseClassFragment())
                .commit()
        }

        mp = MediaPlayer.create(this, R.raw.maintheme)
        mp.isLooping = true
        mp.start()


    }
    override fun onPause() {
        super.onPause()
        if (this::mp.isInitialized && mp.isPlaying) {
            mp.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (this::mp.isInitialized) {
            mp.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::mp.isInitialized) {
            mp.release()
        }
    }
}



