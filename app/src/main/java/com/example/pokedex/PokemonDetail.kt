package com.example.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PokemonDetail : AppCompatActivity() {

    private var pokemonNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        pokemonNumber = intent.getIntExtra(MainActivity.INTENT_POKEMON_KEY, 1)
        val textView: TextView = findViewById(R.id.test)
        textView.text = pokemonNumber.toString()
    }
}
