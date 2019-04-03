package com.example.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetail : AppCompatActivity() {

    private val somethingWrongUrl = "https://i.redd.it/2urjp7yzl1z01.png"
    private val noImageUrl = "https://upload.wikimedia.org/wikipedia/commons/f/fc/No_picture_available.png"
    private var pokemonNumber = 0
    lateinit var pokeService: PokeApi
    lateinit var pokeImageView: ImageView
    var pokemon: Pokemon? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        pokemonNumber = intent.getIntExtra(MainActivity.INTENT_POKEMON_KEY, 1)
        val textView: TextView = findViewById(R.id.test)
        textView.text = pokemonNumber.toString()

        pokeImageView = findViewById(R.id.poke_image_view)

        pokeService = PokeApi.create()
        pokeService.getSinglePokemon(pokemonNumber.toString()).enqueue(object: Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                pokemon = response.body()
                Glide.with(this@PokemonDetail).load(pokemon?.sprites?.frontDefault ?: noImageUrl).into(pokeImageView)
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Glide.with(this@PokemonDetail).load(somethingWrongUrl).into(pokeImageView)
            }
        })


    }
}
