package com.example.wizardingworld.sampledata.booksData

import com.google.gson.annotations.SerializedName

class BookResponse {
    @SerializedName("data")
    lateinit var books: List<Book>
}