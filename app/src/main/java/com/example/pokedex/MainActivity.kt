package com.example.pokedex

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity(), PokemonListRecyclerViewAdapter.PokemonListRecyclerViewClickListener {

    lateinit var pokemonListRecyclerView: RecyclerView
    lateinit var pokeService: PokeApi
    private var list: List<NameUrl> = listOf()

    companion object {
        const val INTENT_POKEMON_KEY = "pokemonNumber"
        const val POKEMON_TOTAL_NUMBER = "807"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokeService = PokeApi.create()
        pokemonListRecyclerView = findViewById(R.id.pokemon_list_recycler_view)
        pokemonListRecyclerView.adapter = PokemonListRecyclerViewAdapter(list, this)
        pokemonListRecyclerView.layoutManager = LinearLayoutManager(this)

        pokeService.getAllPokemon(POKEMON_TOTAL_NUMBER).enqueue(object: Callback<AllPokemon> {
            override fun onResponse(call: Call<AllPokemon>, response: Response<AllPokemon>) {
                list = response.body()?.results ?: throw IllegalStateException()
                val recyclerViewAdapter = pokemonListRecyclerView.adapter as? PokemonListRecyclerViewAdapter
                recyclerViewAdapter?.updateList(list)
            }

            override fun onFailure(call: Call<AllPokemon>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Please close the app and try again", Toast.LENGTH_LONG).show()
            }
        })
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
