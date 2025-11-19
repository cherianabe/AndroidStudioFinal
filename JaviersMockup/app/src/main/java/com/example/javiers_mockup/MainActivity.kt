package com.example.javiers_mockup

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.javiers_mockup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var b : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        // 1. Find the RecyclerView from XML
        val recyclerView = b.myRecycleView

        // 2. Set a layout manager (vertical list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 3. Set the adapter (the class you just created)
        recyclerView.adapter = MyAdapter()
    }
}