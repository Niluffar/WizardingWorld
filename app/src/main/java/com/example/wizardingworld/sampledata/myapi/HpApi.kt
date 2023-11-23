package com.example.wizardingworld.sampledata.myapi

import com.example.wizardingworld.sampledata.charactersData.Character
import retrofit2.Call
import retrofit2.http.GET

interface HpApi {

    @GET("/api/characters")
    fun fetchCharacters(): Call<List<Character>>
}