package com.example.wizardingworld.sampledata.booksData

import com.google.gson.annotations.SerializedName

data class BookAttributes(var slug: String,
                          var author: String,
                          @SerializedName("cover") var coverLink: String,
                          var pages: String,
                          @SerializedName("release_date") var releaseDate: String,
                          var summary: String,
                          var title: String,
                          val dedication: String){

}