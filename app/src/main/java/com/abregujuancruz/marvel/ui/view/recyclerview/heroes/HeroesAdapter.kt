package com.abregujuancruz.marvel.ui.view.recyclerview.heroes

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abregujuancruz.marvel.R
import com.abregujuancruz.marvel.models.Heroes
import com.abregujuancruz.marvel.utils.Constants
import com.abregujuancruz.marvel.ui.view.DetailsActivity

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
        holder.b.constraint.setOnClickListener {
            val heroesId = heroes.id.toString()
            val intent = Intent(it.context, DetailsActivity::class.java)
            intent.putExtra(Constants.EXTRA_ID, heroesId)
            it.context.startActivity(intent)
        }
    }
    
    override fun getItemCount(): Int = listHeroes.size
}