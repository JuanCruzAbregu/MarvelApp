package com.abregujuancruz.marvel.models

import com.google.gson.annotations.SerializedName

data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    @SerializedName("results") val listHeroes: List<Heroes>
)