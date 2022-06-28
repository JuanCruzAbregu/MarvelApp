package com.abregujuancruz.marvel.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abregujuancruz.marvel.network.APIService
import com.abregujuancruz.marvel.models.HeroResponse
import com.abregujuancruz.marvel.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroesViewModel : ViewModel() {
    
    val heroesData = MutableLiveData<HeroResponse>()
    private val apiService = APIService()
    
    fun getListOfHeroes() {
        val call: Call<HeroResponse> = apiService.getListHeroes(
            Constants.TS, Constants.APIKEY, Constants.HASH, Constants.LIMIT
        )
        
        call.enqueue(object : Callback<HeroResponse> {
            override fun onResponse(call: Call<HeroResponse>, response: Response<HeroResponse>) {
                heroesData.postValue(response.body())
            }
            
            override fun onFailure(call: Call<HeroResponse>, t: Throwable) {
                Log.e("Error!: ", t.message.toString())
            }
        })
    }
    
    fun getHeroById(heroId : String){
        val call: Call<HeroResponse> = apiService.getHeroesById(
            heroId.toInt(), Constants.TS, Constants.APIKEY, Constants.HASH
        )
        call.enqueue(object : Callback<HeroResponse> {
            override fun onResponse(call: Call<HeroResponse>, response: Response<HeroResponse>) {
                heroesData.postValue(response.body())
            }
        
            override fun onFailure(call: Call<HeroResponse>, t: Throwable) {
                Log.e("Error!: ", t.message.toString())
            }
        
        })
    }
    
    
    
}