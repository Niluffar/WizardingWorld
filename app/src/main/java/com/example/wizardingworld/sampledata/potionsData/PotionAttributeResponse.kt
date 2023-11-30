package com.example.wizardingworld.sampledata.potionsData

import com.example.wizardingworld.sampledata.moviesData.MovieAttributes
import com.google.gson.annotations.SerializedName

class PotionAttributeResponse {
    @SerializedName("attributes")
    lateinit var attributes: MovieAttributes
}