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
import com.codeplace.mvvmpokemonapp.databinding.FragmentListPokemonBinding
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import com.codeplace.mvvmpokemonapp.ui.home.view.adapter.FragmentListPokemonAdapterCustomized
import com.codeplace.mvvmpokemonapp.ui.home.view.adapter.RecyclerViewClickListener
import com.codeplace.mvvmpokemonapp.ui.home.viewModel.PokemonViewModel
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListPokemonFragment: Fragment(), RecyclerViewClickListener {
    private lateinit var binding: FragmentListPokemonBinding
    private lateinit var adapter: FragmentListPokemonAdapterCustomized
     private val viewModel by viewModel<PokemonViewModel>()
     override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListPokemonBinding.inflate(inflater, container, false)

        initValues()
        initObservables()
        return binding.root
    }
    private fun initValues() {
        viewModel.getPokemonNames()
        viewModel.getFavoritesPokemon()
      }

    private fun initObservables() {
        viewModel.pokemonNames.observe(viewLifecycleOwner) {
            when (it) {
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillPokemonNamesAndGetPokemonInfo(it.data as JSONObject))
                is StateFlow.Error -> (errorMessage(it.errorMessage))
            }
        }
        viewModel.pokemonInfo.observe(viewLifecycleOwner) {
            when (it) {
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillPokemonInfo(it.data as JSONObject))
                is StateFlow.Error -> (errorMessage(it.errorMessage))
            }
        }
        viewModel.pokemonFavorites.observe(viewLifecycleOwner){
            when (it) {
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillFavoritePokemonList(it.data as List<PokemonDb>))
                is StateFlow.Error -> (errorMessage(it.errorMessage))
            }
        }
    }
    private fun fillFavoritePokemonList(result: List<PokemonDb>) {
        viewModel.fillFavoritePokemonList(result)
    }

    private fun fillPokemonNamesAndGetPokemonInfo(result: JSONObject) {
        viewModel.fillPokemonNamesList(result)
        getPokemonInfoByName()
    }

    private fun getPokemonInfoByName(){
        viewModel.getPokemonInfoByName()
    }
    private fun fillPokemonInfo(result: JSONObject) {
        viewModel.fillPokemonInfo(result)
        initRecyclerAdapter()
    }

    private fun initRecyclerAdapter() {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = FragmentListPokemonAdapterCustomized(
                viewModel.synchronizedDataList,
                this@ListPokemonFragment
            )

            recyclerView.adapter = adapter
         }
    }
    private fun loading(loading: Boolean) {
        binding.progressBar.visibility = if (loading) VISIBLE else GONE
    }
    private fun errorMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun addToFavoriteClick(pokemonDb: PokemonDb?, message: String?) {
        viewModel.addPokemonToFavorites(pokemonDb!!)
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun removeFromFavoritesClick(pokemonName: String?, message: String?) {
        viewModel.deletePokemonFromFavorites(pokemonName!!)
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


}

