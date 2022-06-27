package com.abregujuancruz.marvel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.abregujuancruz.marvel.api.APIService
import com.abregujuancruz.marvel.databinding.ActivityMainBinding
import com.abregujuancruz.marvel.models.HeroResponse
import com.abregujuancruz.marvel.models.Heroes
import com.abregujuancruz.marvel.utils.Constants
import com.abregujuancruz.marvel.view.recyclerview.heroes.HeroesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    
    private lateinit var b: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        
        val loader = APIService()
        
        val call: Call<HeroResponse> = loader.getListHeroes(
            Constants.TS, Constants.APIKEY, Constants.HASH, Constants.LIMIT
        )
        call.enqueue(object : Callback<HeroResponse> {
            override fun onResponse(call: Call<HeroResponse>, response: Response<HeroResponse>) {
                val listHeroes: List<Heroes> = response.body()?.listData?.listHeroes ?: emptyList()
                initRecyclerView(listHeroes)
                b.progressbar.visibility = View.GONE
            }
            
            override fun onFailure(call: Call<HeroResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong :(", Toast.LENGTH_SHORT)
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