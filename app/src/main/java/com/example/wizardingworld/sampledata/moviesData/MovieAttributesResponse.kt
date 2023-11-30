package com.example.wizardingworld.sampledata.moviesData

import com.google.gson.annotations.SerializedName

class MovieAttributesResponse {
    @SerializedName("attributes")
    lateinit var attributes: MovieAttributes
}