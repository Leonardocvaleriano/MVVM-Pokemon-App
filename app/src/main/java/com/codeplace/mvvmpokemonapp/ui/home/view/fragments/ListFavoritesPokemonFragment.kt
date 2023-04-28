package com.codeplace.mvvmpokemonapp.ui.home.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codeplace.mvvmpokemonapp.databinding.FragmentFavoritesBinding
import com.codeplace.mvvmpokemonapp.ui.home.viewModel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFavoritesPokemonFragment: Fragment(){

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel by viewModel<PokemonViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
        val pokemonName = arguments?.getString("EXTRA_POKEMON_NAME")
        initValues(pokemonName)

    }

    private fun initValues(pokemonName:String?) {
        viewModel.listPokemonNames
    }
}