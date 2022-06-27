package com.abregujuancruz.marvel.view.recyclerview.comics

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abregujuancruz.marvel.databinding.ItemComicsLayoutBinding

class ComicsViewHolder(view : View) :RecyclerView.ViewHolder(view) {
    
    private val b = ItemComicsLayoutBinding.bind(view)
    
    fun bind(name : String){
        
        b.tvComic.text = name
        
    }
    

}