package com.example.wizardingworld.sampledata

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wizardingworld.sampledata.myapi.PotterFetchr
import com.example.wizardingworld.sampledata.potionsData.Potion

class PotionsViewModel : ViewModel() {
    val potionLiveData: LiveData<List<Potion>>

    init {
        potionLiveData = PotterFetchr().fetchPotions()
    }
}