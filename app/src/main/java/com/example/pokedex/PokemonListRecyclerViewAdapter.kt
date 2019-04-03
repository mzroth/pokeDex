package com.example.pokedex

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class PokemonListRecyclerViewAdapter(val clickListener: PokemonListRecyclerViewClickListener) : RecyclerView.Adapter<PokemonListRecyclerViewHolder>() {
    private val list = listOf("Bulbasaur", "Ivysaur", "Venusaur")

    interface PokemonListRecyclerViewClickListener {
        fun pokemonClicked(number: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PokemonListRecyclerViewHolder {
        return PokemonListRecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_recycler_view_holder, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PokemonListRecyclerViewHolder, position: Int) {
        holder.pokemonListIdView.text = (position + 1).toString()
        holder.pokemonListNameView.text = list[position]
        holder.itemView.setOnClickListener { clickListener.pokemonClicked(position + 1) }
    }

}