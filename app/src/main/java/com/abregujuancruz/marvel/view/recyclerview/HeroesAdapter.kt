package com.abregujuancruz.marvel.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abregujuancruz.marvel.R
import com.abregujuancruz.marvel.models.Heroes

class HeroesAdapter(private val listHeroes: List<Heroes>) :
    RecyclerView.Adapter<HeroesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeroesViewHolder(
            layoutInflater.inflate(
                R.layout.items_characters_layout,
                parent,
                false
            )
        )
    }
    
    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val heroes = listHeroes[position]
        holder.bind(heroes.name, heroes.thumbnail)
    }
    
    override fun getItemCount(): Int = listHeroes.size
}