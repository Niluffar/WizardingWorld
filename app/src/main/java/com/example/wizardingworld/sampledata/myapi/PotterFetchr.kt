package com.example.wizardingworld.sampledata.myapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wizardingworld.sampledata.booksData.Book
import com.example.wizardingworld.sampledata.booksData.BookResponse
import com.example.wizardingworld.sampledata.moviesData.Movie
import com.example.wizardingworld.sampledata.moviesData.MovieResponse
import com.example.wizardingworld.sampledata.potionsData.Potion
import com.example.wizardingworld.sampledata.potionsData.PotionResponse
import com.example.wizardingworld.sampledata.spellsData.Spell
import com.example.wizardingworld.sampledata.spellsData.SpellResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PotterFetchr {
    private val potterApi: PotterApi

    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.potterdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        potterApi = retrofit.create(PotterApi::class.java)
    }

    fun fetchBooks(): LiveData<List<Book>> {
        val responseLiveData: MutableLiveData<List<Book>> = MutableLiveData()
        val potterRequest: Call<BookResponse> = potterApi.fetchBooks()
        potterRequest.enqueue(object : Callback<BookResponse> {
            override fun onFailure(call: Call<BookResponse>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<BookResponse>,
                response: Response<BookResponse>
            ) {


                val bookResponse: BookResponse? = response.body()
                var books: List<Book> = bookResponse?.books?: mutableListOf()


                responseLiveData.value = books
            }
        })
        return responseLiveData
    }

    fun fetchSpells(): LiveData<List<Spell>> {
        val responseLiveData: MutableLiveData<List<Spell>> = MutableLiveData()
        val potterRequest: Call<SpellResponse> = potterApi.fetchSpells()
        potterRequest.enqueue(object : Callback<SpellResponse> {
            override fun onFailure(call: Call<SpellResponse>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<SpellResponse>,
                response: Response<SpellResponse>
            ) {


                val spellResponse: SpellResponse? = response.body()
                var spells: List<Spell> = spellResponse?.spells?: mutableListOf()


                responseLiveData.value = spells
            }
        })
        return responseLiveData
    }

    fun fetchMovies(): LiveData<List<Movie>> {
        val responseLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
        val potterRequest: Call<MovieResponse> = potterApi.fetchMovies()
        potterRequest.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {


                val movieResponse: MovieResponse? = response.body()
                var movies: List<Movie> = movieResponse?.movies?: mutableListOf()


                responseLiveData.value = movies
            }
        })
        return responseLiveData
    }

    fun fetchPotions(): LiveData<List<Potion>> {
        val responseLiveData: MutableLiveData<List<Potion>> = MutableLiveData()
        val potterRequest: Call<PotionResponse> = potterApi.fetchPotions()
        potterRequest.enqueue(object : Callback<PotionResponse> {
            override fun onFailure(call: Call<PotionResponse>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<PotionResponse>,
                response: Response<PotionResponse>
            ) {


                val potionResponse: PotionResponse? = response.body()
                var potions: List<Potion> = potionResponse?.potions?: mutableListOf()


                responseLiveData.value = potions
            }
        })
        return responseLiveData
    }
}