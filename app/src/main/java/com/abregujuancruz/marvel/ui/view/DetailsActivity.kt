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
import com.abregujuancruz.marvel.ui.viewmodel.DetailsViewModel
import com.abregujuancruz.marvel.ui.viewmodel.HeroesViewModel
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        detailsViewModel.getHeroById(intent.getStringExtra(Constants.EXTRA_ID).toString())
        
        detailsViewModel.heroesData.observe(this) {
            val hero: Heroes = it.listData.listHeroes[0]
            binding.tvHeroTitle.text = hero.name
            binding.tvHeroDescription.text = hero.description
            Picasso.get().load(hero.thumbnail.path + "." + hero.thumbnail.extension)
                .into(binding.ivHeroImg)
            initRecyclerView(hero.comics.items)
        }
    }
    
    private fun initRecyclerView(listItem: List<Items>) {
        val adapter = ComicsAdapter(listItem)
        val manager = LinearLayoutManager(this)
        binding.rvComics.adapter = adapter
        binding.rvComics.layoutManager = manager
    }
}