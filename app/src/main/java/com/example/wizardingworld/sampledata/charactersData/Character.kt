package com.example.wizardingworld.sampledata.charactersData

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Character(val id: String,
                     val name: String,
                     val ancestry: String,
                     val gender: String,
                     val hairColour: String,
                     val house: String,
                     val image: String,
                     val patronus: String,
                     val wizard: Boolean,
                     val alive: Boolean,
                     val actor: String,
                     val dateOfBirth: String,
                     val died: String,
                     val eyeColour: String,) : Serializable{

}
