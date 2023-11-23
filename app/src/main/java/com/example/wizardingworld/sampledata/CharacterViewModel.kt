package com.example.wizardingworld.sampledata

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wizardingworld.sampledata.charactersData.Character
import com.example.wizardingworld.sampledata.myapi.HpFetchr

class CharacterViewModel : ViewModel() {
    val characterLiveData: LiveData<List<Character>>

    init{
        characterLiveData = HpFetchr().fetchCharacters()
    }
}