package com.example.wizardingworld.sampledata.myapi

import com.example.wizardingworld.sampledata.booksData.BookResponse
import com.example.wizardingworld.sampledata.moviesData.MovieResponse
import com.example.wizardingworld.sampledata.potionsData.PotionResponse
import com.example.wizardingworld.sampledata.spellsData.SpellResponse
import retrofit2.Call
import retrofit2.http.GET

interface PotterApi {

    @GET("/v1/books")
    fun fetchBooks(): Call<BookResponse>

    @GET("/v1/spells")
    fun fetchSpells(): Call<SpellResponse>

    @GET("/v1/movies")
    fun fetchMovies(): Call<MovieResponse>

    @GET("/v1/potions")
    fun fetchPotions(): Call<PotionResponse>



}
