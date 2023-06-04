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
import com.codeplace.mvvmpokemonapp.ui.home.view.adapters.FragmentListPokemonsFavoritesAdapter
import com.codeplace.mvvmpokemonapp.ui.home.view.adapters.PokemonFavoriteItemClickListener
import com.codeplace.mvvmpokemonapp.ui.home.viewModel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFavoritesPokemonFragment: Fragment(), PokemonFavoriteItemClickListener {

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
        viewModel.getFavoritesPokemon()
    }

    private fun initObservables() {
        viewModel.pokemonFavorites.observe(viewLifecycleOwner){
            when(it){
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillFavoritePokemonList(it.data as List<PokemonDb>))
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
        Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
    }
    private fun fillFavoritePokemonList(result:List<PokemonDb>){
        viewModel.fillFavoritePokemonList(result)
        initRecyclerAdapter()
    }
    private fun initRecyclerAdapter() {
        with(binding){
            adapter = FragmentListPokemonsFavoritesAdapter(
                viewModel.pokemonFavoritesList,
                this@ListFavoritesPokemonFragment
            )
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = adapter
        }
    }

    override fun removeFromFavoriteClick(name: String?, message: String) {
        viewModel.deletePokemonFromFavorites(name)
        Toast.makeText(activity, "Pokemon has been removed from favorites.", Toast.LENGTH_SHORT).show()
        viewModel.updatePokemonFavorites()
        initObservables()
        adapter.notifyDataSetChanged()

    }

}