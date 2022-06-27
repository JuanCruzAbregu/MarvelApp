package com.abregujuancruz.marvel.view.recyclerview.comics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abregujuancruz.marvel.R
import com.abregujuancruz.marvel.models.Items

class ComicsAdapter(private val listComic: List<Items>) : RecyclerView.Adapter<ComicsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComicsViewHolder(
            layoutInflater.inflate(
                R.layout.item_comics_layout,
                parent,
                false
            )
        )
    }
    
    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val comic = listComic[position]
        holder.bind(comic.name)
    }
    
    override fun getItemCount(): Int = listComic.size
}