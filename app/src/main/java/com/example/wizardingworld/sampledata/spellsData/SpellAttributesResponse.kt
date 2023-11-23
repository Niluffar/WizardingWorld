package com.example.wizardingworld.sampledata.spellsData

import com.google.gson.annotations.SerializedName

class SpellAttributesResponse {
    @SerializedName("attributes")
    lateinit var spellAttributes: SpellAttributes
}