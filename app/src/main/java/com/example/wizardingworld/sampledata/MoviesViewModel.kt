package com.example.wizardingworld.sampledata

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wizardingworld.sampledata.moviesData.Movie
import com.example.wizardingworld.sampledata.myapi.PotterFetchr

class MoviesViewModel : ViewModel() {
    val movieLiveData: LiveData<List<Movie>>

    init {
        movieLiveData = PotterFetchr().fetchMovies()
    }
}