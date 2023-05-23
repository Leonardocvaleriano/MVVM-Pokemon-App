package com.codeplace.mvvmpokemonapp.ui.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codeplace.mvvmpokemonapp.databinding.PokemonItemsBinding
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.ui.home.view.models.Pokemon

class FragmentListPokemonsFavoritesAdapter(
    private var listPokemonsDb: List<PokemonDb>
    ): RecyclerView.Adapter<FragmentListPokemonsFavoritesAdapter.FragmentListPokemonsFavoritesHolder>(){

    inner class FragmentListPokemonsFavoritesHolder(val binding: PokemonItemsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FragmentListPokemonsFavoritesHolder {
        return FragmentListPokemonsFavoritesHolder(PokemonItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return   listPokemonsDb.size
    }

    override fun onBindViewHolder(holder: FragmentListPokemonsFavoritesHolder, position: Int) {
        with(holder.binding){

            txtPokemonName.text  = listPokemonsDb[position].pokemonName
            txtPokemonAbility.text = listPokemonsDb[position].pokemonAbility
            txtPokemonType.text = listPokemonsDb[position].pokemonType
            txtPokemonMove.text = listPokemonsDb[position].pokemonMove
            Glide.with(holder.itemView.context)
                .load(listPokemonsDb[position].pokemonImg)
                .into(imgPokemon)
         }
     }

}