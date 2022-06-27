package com.abregujuancruz.marvel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.abregujuancruz.marvel.api.APIService
import com.abregujuancruz.marvel.databinding.ActivityDetailsBinding
import com.abregujuancruz.marvel.models.HeroResponse
import com.abregujuancruz.marvel.models.Items
import com.abregujuancruz.marvel.utils.Constants
import com.abregujuancruz.marvel.view.recyclerview.comics.ComicsAdapter
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    
    private lateinit var b: ActivityDetailsBinding
    private lateinit var loader: APIService
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(b.root)
        
        loader = APIService()
        
        val heroId = intent.getStringExtra(Constants.EXTRA_ID)
        val call: Call<HeroResponse> = loader.getHeroesById(
            heroId!!.toInt(), Constants.TS, Constants.APIKEY, Constants.HASH
        )
        
        call.enqueue(object : Callback<HeroResponse> {
            override fun onResponse(call: Call<HeroResponse>, response: Response<HeroResponse>) {
                val hero = response.body()?.listData?.listHeroes ?: emptyList()
                b.tvHeroTitle.text = hero[0].name
                b.tvHeroDescription.text = hero[0].description
                val url: String = hero[0].thumbnail.path + "." + hero[0].thumbnail.extension
                Picasso.get().load(url).into(b.ivHeroImg)
                val listComics: List<Items> = hero[0].comics.items
                initRecyclerView(listComics)
            }
            
            override fun onFailure(call: Call<HeroResponse>, t: Throwable) {
                Toast.makeText(this@DetailsActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
            
        })
        
        
    }
    
    private fun initRecyclerView(listItem: List<Items>) {
        val adapter = ComicsAdapter(listItem)
        b.rvComics.adapter = adapter
        val manager = LinearLayoutManager(this)
        b.rvComics.layoutManager = manager
    }
}