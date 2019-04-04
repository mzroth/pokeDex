package com.example.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

        pokeImageView = findViewById(R.id.poke_image_view)

        pokeService = PokeApi.create()
        pokeService.getSinglePokemon(pokemonNumber.toString()).enqueue(object: Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                pokemon = response.body()
                supportActionBar?.title = pokemon?.name?.capitalize() + " #" + pokemonNumber
                val textView = findViewById<TextView>(R.id.height_text_view)
                textView.text = getString(R.string.height, pokemon?.height.toString())
                findViewById<TextView>(R.id.weight_text_view).text = getString(R.string.weight, pokemon?.weight.toString())
                findViewById<TextView>(R.id.type1_text_view).text = getString(R.string.type, pokemon?.types?.get(0)?.type?.name?.capitalize())
                Glide.with(this@PokemonDetail).load(pokemon?.sprites?.frontDefault ?: noImageUrl).into(pokeImageView)

                if(pokemon?.types?.getOrNull(1) != null) {
                    val view = findViewById<TextView>(R.id.type2_text_view)
                    view.background = getDrawable(R.drawable.text_view_background)
                    view.text = getString(R.string.type, pokemon?.types?.get(1)?.type?.name?.capitalize())

                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Glide.with(this@PokemonDetail).load(somethingWrongUrl).into(pokeImageView)
            }
        })


    }
}
