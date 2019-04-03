package com.example.pokedex

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class PokemonListRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val pokemonListIdView: TextView = view.findViewById(R.id.pokemon_list_id_text_view)
    val pokemonListNameView: TextView = view.findViewById(R.id.pokemon_list_name_text_view)

}
