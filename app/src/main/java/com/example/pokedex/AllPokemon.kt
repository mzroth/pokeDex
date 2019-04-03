package com.example.pokedex

import com.google.gson.annotations.SerializedName

class AllPokemon {
    @SerializedName("results")
    val results: List<NameUrl>? = null
}
