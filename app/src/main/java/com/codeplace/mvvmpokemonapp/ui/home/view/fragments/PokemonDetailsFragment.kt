package com.codeplace.mvvmpokemonapp.ui.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codeplace.mvvmpokemonapp.databinding.FragmentPokemonDetailsBinding
import com.codeplace.mvvmpokemonapp.util.capitalize

class PokemonDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailsBinding

    private val args: PokemonDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)

        with(binding){
            txtPokemonName.text = capitalize(args.pokemonName)

                Glide.with(this@PokemonDetailsFragment)
                    .load(args.urlPokemonImage)
                     .fitCenter()
                    .into(imgPokemon)
        }


        return binding.root

    }
}