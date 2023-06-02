package com.codeplace.mvvmpokemonapp.ui.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeplace.mvvmpokemonapp.databinding.PokemonItemsBinding
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonInfos
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonNames
import kotlin.math.min

class PokemonListAdapter(

    private val pokemonNamesList:List<PokemonNames>,
    private val pokemonInfoList:List<PokemonInfos>,
    private val pokemonFavoriteList:List<PokemonDb>
):RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: PokemonItemsBinding):RecyclerView.ViewHolder(binding.root)

    // When scrolled, recycle all the views, changing all the content to show the others pokemon
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PokemonItemsBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun getItemCount(): Int {
        return min(pokemonNamesList.size,pokemonInfoList.size)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemonNames = pokemonNamesList[position]
        val pokemonInfos = pokemonInfoList[position]

        with(holder.binding){
            txtPokemonName.text = pokemonNames.name

            //icFavorite.isActivated

//            icFavorite.setOnClickListener {
//                if ()
//
//            }
        }
    }

}