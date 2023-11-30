package com.example.wizardingworld.sampledata.potionsData

import com.google.gson.annotations.SerializedName

class PotionResponse {
    @SerializedName("data")
    lateinit var potions: List<Potion>
}