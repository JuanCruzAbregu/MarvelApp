package com.abregujuancruz.marvel.api

import com.abregujuancruz.marvel.models.HeroResponse
import com.abregujuancruz.marvel.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService : MarvelAPI {
    
    private lateinit var retrofit: Retrofit
    private lateinit var marvelAPI: MarvelAPI
    
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    override fun getListHeroes(
        ts: String,
        apikey: String,
        hash: String,
        limit: String
    ): Call<HeroResponse> {
        retrofit = getRetrofit()
        marvelAPI = retrofit.create(MarvelAPI::class.java)
        return marvelAPI.getListHeroes(ts, apikey, hash, limit)
    }
    
    override fun getHeroesById(
        id: Int,
        ts: String,
        apikey: String,
        hash: String
    ): Call<HeroResponse> {
        retrofit = getRetrofit()
        marvelAPI = retrofit.create(MarvelAPI::class.java)
        return marvelAPI.getHeroesById(id, ts, apikey, hash)
    }
    
}