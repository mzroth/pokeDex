package com.example.pokedex

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity(), PokemonListRecyclerViewAdapter.PokemonListRecyclerViewClickListener {

    lateinit var pokemonListRecyclerView: RecyclerView

    companion object {
        const val INTENT_POKEMON_KEY = "pokemonNumber"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonListRecyclerView = findViewById(R.id.pokemon_list_recycler_view)
        pokemonListRecyclerView.adapter = PokemonListRecyclerViewAdapter(this)
        pokemonListRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun pokemonClicked(number: Int) {
        showPokemonDetail(number)
    }

    private fun showPokemonDetail(number: Int) {
        val pokemonDetailIntent = Intent(this, PokemonDetail::class.java)
        pokemonDetailIntent.putExtra(INTENT_POKEMON_KEY, number)
        startActivity(pokemonDetailIntent)
    }
}
