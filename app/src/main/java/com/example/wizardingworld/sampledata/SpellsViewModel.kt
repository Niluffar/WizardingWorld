package com.example.wizardingworld.sampledata

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wizardingworld.sampledata.myapi.PotterFetchr
import com.example.wizardingworld.sampledata.spellsData.Spell

class SpellsViewModel : ViewModel() {
    val spellsLiveData: LiveData<List<Spell>>

    init{
        spellsLiveData = PotterFetchr().fetchSpells()
    }

}