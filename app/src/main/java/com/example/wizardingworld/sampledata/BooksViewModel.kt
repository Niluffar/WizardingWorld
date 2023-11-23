package com.example.wizardingworld.sampledata

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wizardingworld.sampledata.booksData.Book
import com.example.wizardingworld.sampledata.myapi.PotterFetchr


    class BooksViewModel : ViewModel() {
        val bookLiveData: LiveData<List<Book>>
        init {
            bookLiveData = PotterFetchr().fetchBooks()
        }
    }
