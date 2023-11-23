package com.example.wizardingworld.sampledata.myapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wizardingworld.sampledata.charactersData.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HpFetchr {

    private val hpApi: HpApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        hpApi = retrofit.create(HpApi::class.java)
    }

    fun fetchCharacters(): LiveData<List<Character>>{
        val responseLiveData: MutableLiveData<List<Character>> = MutableLiveData()
        val hpRequest: Call<List<Character>> = hpApi.fetchCharacters()
        hpRequest.enqueue(object : Callback<List<Character>> {
            override fun onFailure(call: Call<List<Character>>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<List<Character>>,
                response: Response<List<Character>>
            ) {


                val characters: List<Character>? = response.body()?: mutableListOf()


                responseLiveData.value = characters
            }
        })
        return responseLiveData
    }
}