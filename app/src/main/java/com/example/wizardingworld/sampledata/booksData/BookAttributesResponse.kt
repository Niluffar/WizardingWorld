package com.example.wizardingworld.sampledata.booksData

import com.google.gson.annotations.SerializedName

class BookAttributesResponse {
    @SerializedName("attributes")
    lateinit var attributes: BookAttributes
}