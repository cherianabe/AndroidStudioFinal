package com.example.javiers_mockup
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // This holds the views for each item
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageView)
        val button: Button = view.findViewById(R.id.btn)
    }

    // Tells RecyclerView how to create a new item (using your card layout)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_row, parent, false)   // <-- use your item layout name here
        return ViewHolder(view)
    }

    // How many items to show? For now, let’s just show 5 cards.
    override fun getItemCount(): Int = 5

    // Bind data to each item (position = row index)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Example: you can change image or button text here if you want
        holder.button.text = "Button #$position"

        holder.button.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MainActivity2::class.java)
            context.startActivity(intent)
        }
    }
}