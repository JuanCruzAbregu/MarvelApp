package com.abregujuancruz.marvel.models

import com.google.gson.annotations.SerializedName

data class HeroResponse(
    @SerializedName("data") val listData : Data
)

