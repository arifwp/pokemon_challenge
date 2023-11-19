package com.example.pokemon_challenge.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon_challenge.R
import com.example.pokemon_challenge.databinding.LayoutPokemonBinding
import com.example.pokemon_challenge.response.PokemonResultsItem
import com.example.pokemon_challenge.utils.onClick


class PokemonAdapter(dataPokemon1: Int, private var dataPokemon: List<PokemonResultsItem>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(val binding: LayoutPokemonBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonAdapter.PokemonViewHolder {
        val binding = LayoutPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonAdapter.PokemonViewHolder, position: Int) {
        val data = dataPokemon[position]

        val url = data.url
        val uri = Uri.parse(url)
        val parameters = uri!!.pathSegments
        val param = parameters[parameters.size - 1]


        with(holder.binding){
            tvNamePokemon.text = data.name
            Glide.with(holder.itemView.context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${param}.png")
                .placeholder(R.drawable.example_img_pokemon)
                .into(ivPokemon)

        }
        holder.itemView.setOnClickListener {
            listenerOnClick?.onClickItem(param, data.name.toString(), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${param}.png")
        }
    }

    var listenerOnClick: onClick? = null

    override fun getItemCount(): Int = dataPokemon.size

    fun setPokemonAdapter(pokemon: List<PokemonResultsItem>){
        this.dataPokemon = pokemon
        notifyDataSetChanged()
    }
}