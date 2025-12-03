package com.example.dndbuilder.ui

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
    }
}
