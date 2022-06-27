package com.abregujuancruz.marvel.api

import com.abregujuancruz.marvel.models.HeroResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {
    @GET("characters")
    fun getListHeroes(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: String
    ): Call<HeroResponse>
    
    
    @GET("characters/{characterId}")
    fun getHeroesById(
        @Path("characterId") id: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): Call<HeroResponse>
    
}