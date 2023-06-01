package com.codeplace.mvvmpokemonapp.ui.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeplace.mvvmpokemonapp.databinding.FragmentFavoritesBinding
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import com.codeplace.mvvmpokemonapp.ui.home.view.adapter.FragmentListPokemonsFavoritesAdapter
import com.codeplace.mvvmpokemonapp.ui.home.viewModel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFavoritesPokemonFragment: Fragment(){

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FragmentListPokemonsFavoritesAdapter

    private val viewModel by viewModel<PokemonViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        initValues()
        initObservables()
        return binding.root

    }
    private fun initValues() {
        viewModel.getAllFavoritesPokemon()
    }

    private fun initObservables() {
        viewModel.allPokemonsAsFavorites.observe(viewLifecycleOwner){
            when(it){
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillFavoritesPokemon(it.data as List<PokemonDb>))
                is StateFlow.Error -> (errorMessage(it.errorMessage))
            }
        }
    }
    private fun loading(loading: Boolean) {
        with(binding){
            progressBar.visibility = if(loading) VISIBLE else GONE
        }
    }
    private fun errorMessage(errorMessage: String) {
        Toast.makeText(activity, "$errorMessage", Toast.LENGTH_SHORT).show()
    }
    fun fillFavoritesPokemon(result:List<PokemonDb>){
        viewModel.fillListFavoritePokemons(result)
        initRecyclerAdapter()
    }
    private fun initRecyclerAdapter() {
        with(binding){
            adapter = FragmentListPokemonsFavoritesAdapter(
                viewModel.allPokemonsAsFavorites_
            )
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = adapter
        }
    }
}