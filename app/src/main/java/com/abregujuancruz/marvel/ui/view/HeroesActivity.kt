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
    
    private lateinit var b: ActivityHeroesBinding
    private val  heroesViewModel: HeroesViewModel by viewModels()
    private lateinit var listHeroes: List<Heroes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityHeroesBinding.inflate(layoutInflater)
        setContentView(b.root)
        
        heroesViewModel.getListOfHeroes()
        heroesViewModel.heroesData.observe(this) {
            listHeroes = it.listData.listHeroes
            initRecyclerView(listHeroes)
        }
    
    }
    
    private fun initRecyclerView(listHeroes: List<Heroes>) {
        val adapter = HeroesAdapter(listHeroes)
        b.rvHeroes.adapter = adapter
        val manager = LinearLayoutManager(this)
        b.rvHeroes.layoutManager = manager
        b.progressbar.visibility = View.GONE
    }
}