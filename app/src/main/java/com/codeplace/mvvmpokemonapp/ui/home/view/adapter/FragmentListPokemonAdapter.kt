package com.codeplace.mvvmpokemonapp.ui.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeplace.mvvmpokemonapp.databinding.PokemonItemsBinding
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonDetails
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonName

class FragmentListPokemonAdapter(val listPokemonNames: List<PokemonName>,
                                 val listPokemonDetails: List<PokemonDetails>):RecyclerView.Adapter<FragmentListPokemonAdapter.ViewHolder>() {
    private lateinit var binding: PokemonItemsBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = PokemonItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listPokemonNames.size


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setData(listPokemonNames[position],listPokemonDetails[position])
    }

    inner class ViewHolder(private val itemsV: PokemonItemsBinding) : RecyclerView.ViewHolder(itemsV.root) {
        fun setData(listPokemonNames: PokemonName, listPokemonDetails: PokemonDetails,) {

            listPokemonDetails.sprites.front_default


        }
    }

}
