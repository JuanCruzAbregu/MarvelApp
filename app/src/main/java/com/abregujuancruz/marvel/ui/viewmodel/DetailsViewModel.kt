package com.abregujuancruz.marvel.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abregujuancruz.marvel.models.HeroResponse
import com.abregujuancruz.marvel.network.APIService
import com.abregujuancruz.marvel.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {
    
    private val apiService = APIService()
    
    private val _heroesData = MutableLiveData<HeroResponse>()
    val heroesData: LiveData<HeroResponse> get() = _heroesData
    
    
    fun getHeroById(heroId: String) {
        viewModelScope.launch {
            val call: Call<HeroResponse> = apiService.getHeroesById(
                heroId.toInt(), Constants.TS, Constants.APIKEY, Constants.HASH
            )
            call.enqueue(object : Callback<HeroResponse> {
                override fun onResponse(call: Call<HeroResponse>, response: Response<HeroResponse>) {
                    _heroesData.postValue(response.body())
                }
                
                override fun onFailure(call: Call<HeroResponse>, t: Throwable) {
                    Log.e("Error!: ", t.message.toString())
                }
            })
        }
        
    }
    
    
}