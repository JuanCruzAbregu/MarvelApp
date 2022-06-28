package com.abregujuancruz.marvel.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.abregujuancruz.marvel.databinding.ActivityDetailsBinding
import com.abregujuancruz.marvel.models.Heroes
import com.abregujuancruz.marvel.models.Items
import com.abregujuancruz.marvel.utils.Constants
import com.abregujuancruz.marvel.ui.view.recyclerview.comics.ComicsAdapter
import com.abregujuancruz.marvel.ui.viewmodel.HeroesViewModel
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    
    private lateinit var b: ActivityDetailsBinding
    private val  heroesViewModel: HeroesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(b.root)
        
        heroesViewModel.getHeroById(intent.getStringExtra(Constants.EXTRA_ID).toString())
        
        heroesViewModel.heroesData.observe(this) {
            val hero : Heroes = it.listData.listHeroes[0]
            b.tvHeroTitle.text = hero.name
            b.tvHeroDescription.text = hero.description
            val url = hero.thumbnail.path + "." + hero.thumbnail.extension
            Picasso.get().load(url).into(b.ivHeroImg)
            val listItems : List<Items> = hero.comics.items
            initRecyclerView(listItems)
        }
    }
    
    private fun initRecyclerView(listItem: List<Items>) {
        val adapter = ComicsAdapter(listItem)
        val manager = LinearLayoutManager(this)
        b.rvComics.adapter = adapter
        b.rvComics.layoutManager = manager
    }
}