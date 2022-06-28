package com.abregujuancruz.marvel.ui.view.recyclerview.heroes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abregujuancruz.marvel.databinding.ItemsCharactersLayoutBinding
import com.abregujuancruz.marvel.models.Urls
import com.squareup.picasso.Picasso

class HeroesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    
    val b = ItemsCharactersLayoutBinding.bind(view)
    
    fun bind(name: String, thumbnail: Urls) {
        b.tvHeroName.text = name
        val image = thumbnail.path + "." + thumbnail.extension
        Picasso.get().load(image).into(b.ivHero)
    }
    
}