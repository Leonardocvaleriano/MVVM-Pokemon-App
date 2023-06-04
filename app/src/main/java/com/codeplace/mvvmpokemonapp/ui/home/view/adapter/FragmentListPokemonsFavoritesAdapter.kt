package com.codeplace.mvvmpokemonapp.ui.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codeplace.mvvmpokemonapp.databinding.PokemonItemsBinding
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb

class FragmentListPokemonsFavoritesAdapter(
    private var pokemonFavoriteList: List<PokemonDb>
    ): RecyclerView.Adapter<FragmentListPokemonsFavoritesAdapter.FragmentListPokemonsFavoritesHolder>(){

    inner class FragmentListPokemonsFavoritesHolder(val binding: PokemonItemsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FragmentListPokemonsFavoritesHolder {
        return FragmentListPokemonsFavoritesHolder(PokemonItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = pokemonFavoriteList.size

    override fun onBindViewHolder(holder: FragmentListPokemonsFavoritesHolder, position: Int) {


        with(holder.binding){
            val item = pokemonFavoriteList[position]
            with(item){
                txtPokemonName.text  = pokemonName
                txtPokemonAbility.text = pokemonAbility
                txtPokemonType.text = pokemonType
                txtPokemonMove.text = pokemonMove
                Glide.with(holder.itemView.context)
                    .load(pokemonImg)
                    .into(imgPokemon)
            }
         }
     }
}