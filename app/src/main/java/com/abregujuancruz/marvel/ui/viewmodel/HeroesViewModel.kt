package com.abregujuancruz.marvel.ui.viewmodel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abregujuancruz.marvel.network.APIService
import com.abregujuancruz.marvel.models.HeroResponse
import com.abregujuancruz.marvel.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroesViewModel : ViewModel() {
    private val apiService = APIService()

    private val _heroesData = MutableLiveData<HeroResponse>()
    val heroesData: LiveData<HeroResponse> get() = _heroesData
    
    private val _visibility = MutableLiveData<Boolean>()
    val visibility: LiveData<Boolean> get() = _visibility
    
    fun getListOfHeroes() {
        viewModelScope.launch {
            val call: Call<HeroResponse> = apiService.getListHeroes(
                Constants.TS, Constants.APIKEY, Constants.HASH, Constants.LIMIT
            )
            
            call.enqueue(object : Callback<HeroResponse> {
                override fun onResponse(
                    call: Call<HeroResponse>,
                    response: Response<HeroResponse>
                ) {
                    _heroesData.postValue(response.body())
                }
                
                override fun onFailure(call: Call<HeroResponse>, t: Throwable) {
                    Log.e("Error!: ", t.message.toString())
                }
            })
        }
        
    }
    
    
    
    fun controlVisibility() {
        viewModelScope.launch {
            Handler(Looper.getMainLooper()).postDelayed({
                _visibility.value = true
            }, 1000)
        }
    }
}