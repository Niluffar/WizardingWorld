package com.example.wizardingworld.sampledata.moviesData

import com.google.gson.annotations.SerializedName

data class MovieAttributes(val title: String,
                           val trailer: String,
                           val poster: String,
                           @SerializedName("release_date") val releaseDate: String,
                           @SerializedName("running_time") val runningTime: String,
                           val summary: String){

}
