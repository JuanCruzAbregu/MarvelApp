package com.abregujuancruz.marvel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.abregujuancruz.marvel.api.APIService
import com.abregujuancruz.marvel.databinding.ActivityHeroesBinding
import com.abregujuancruz.marvel.models.HeroResponse
import com.abregujuancruz.marvel.models.Heroes
import com.abregujuancruz.marvel.utils.Constants
import com.abregujuancruz.marvel.view.recyclerview.heroes.HeroesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroesActivity : AppCompatActivity() {
    
    private lateinit var b: ActivityHeroesBinding
    private lateinit var apiService: APIService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityHeroesBinding.inflate(layoutInflater)
        setContentView(b.root)
        
        apiService = APIService()
        getListOfHeroes()
    }
    
    private fun getListOfHeroes() {
        val call: Call<HeroResponse> = apiService.getListHeroes(
            Constants.TS, Constants.APIKEY, Constants.HASH, Constants.LIMIT
        )
        call.enqueue(object : Callback<HeroResponse> {
            override fun onResponse(call: Call<HeroResponse>, response: Response<HeroResponse>) {
                val listHeroes: List<Heroes> = response.body()?.listData?.listHeroes ?: emptyList()
                initRecyclerView(listHeroes)
                b.progressbar.visibility = View.GONE
            }
        
            override fun onFailure(call: Call<HeroResponse>, t: Throwable) {
                Toast.makeText(this@HeroesActivity, "Something went wrong :(", Toast.LENGTH_SHORT)
                    .show()
                b.progressbar.visibility = View.GONE
            }
        })
    }
    
    private fun initRecyclerView(listHeroes: List<Heroes>) {
        val adapter = HeroesAdapter(listHeroes)
        b.rvHeroes.adapter = adapter
        val manager = LinearLayoutManager(this)
        b.rvHeroes.layoutManager = manager
    }
}