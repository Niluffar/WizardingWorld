package com.example.wizardingworld.sampledata.spellsData

import com.google.gson.annotations.SerializedName

class SpellResponse {
    @SerializedName("data")
    lateinit var spells: List<Spell>
}