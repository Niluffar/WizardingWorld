package com.example.wizardingworld.sampledata.moviesData

import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("data")
    lateinit var movies: List<Movie>
}