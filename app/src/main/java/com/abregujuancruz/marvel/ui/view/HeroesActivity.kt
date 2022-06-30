package com.abregujuancruz.marvel.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.abregujuancruz.marvel.databinding.ActivityHeroesBinding
import com.abregujuancruz.marvel.models.Heroes
import com.abregujuancruz.marvel.ui.view.recyclerview.heroes.HeroesAdapter
import com.abregujuancruz.marvel.ui.viewmodel.HeroesViewModel

class HeroesActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityHeroesBinding
    private val heroesViewModel: HeroesViewModel by viewModels()
    private lateinit var listHeroes: List<Heroes>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        heroesViewModel.getListOfHeroes()
        heroesViewModel.heroesData.observe(this) {
            listHeroes = it.listData.listHeroes
            initRecyclerView(listHeroes)
        }
    }
    
    private fun initRecyclerView(listHeroes: List<Heroes>) {
        binding.rvHeroes.adapter = HeroesAdapter(listHeroes)
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        
        heroesViewModel.controlVisibility()
        heroesViewModel.visibility.observe(this) {
            binding.rvHeroes.visibility = if(it) View.VISIBLE else View.GONE
            binding.tvTitleList.visibility = if(it) View.VISIBLE else View.GONE
            binding.shimmerLayout.visibility = if(it) View.GONE else View.VISIBLE
        }
        
    }
}