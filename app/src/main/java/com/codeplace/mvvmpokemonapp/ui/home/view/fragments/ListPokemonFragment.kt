package com.codeplace.mvvmpokemonapp.ui.home.view.fragments

import com.codeplace.mvvmpokemonapp.ui.home.view.adapter.FragmentListPokemonAdapter
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
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import com.codeplace.mvvmpokemonapp.ui.home.viewModel.PokemonViewModel
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListPokemonFragment: Fragment() {

    private lateinit var binding: FragmentListPokemonBinding
    private lateinit var adapter: FragmentListPokemonAdapter
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
        viewModel.getPokemonList()
    }

    private fun initObservables() {
        viewModel.pokemonListNames.observe(viewLifecycleOwner) {
            when (it) {
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillListPokemonName(it.data as JSONObject))
                is StateFlow.Error -> (errorMessage(it.errorMessage))
            }
        }
        viewModel.pokemonDetails.observe(viewLifecycleOwner) {
            when (it) {
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillListPokemonDetails(it.data as JSONObject))
                is StateFlow.Error -> (errorMessage(it.errorMessage))
            }
        }
    }

    private fun fillListPokemonName(result: JSONObject) {
        viewModel.fillListPokemonNames(result)
        viewModel.initPokemonDetails()
    }

    private fun fillListPokemonDetails(result: JSONObject) {
        viewModel.fillListPokemonDetails(result)
        if (viewModel.listPokemonDetails.size >= viewModel.listPokemonNames.size) {
            initRecyclerAdapter()
        }
    }

    private fun initRecyclerAdapter() {
        with(binding) {
            adapter = FragmentListPokemonAdapter(
                viewModel.listPokemonNames,
                viewModel.listPokemonDetails,
             )
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = adapter
        }
    }

    private fun loading(loading: Boolean) {
        binding.progressBar.visibility = if (loading) VISIBLE else GONE
    }

    private fun errorMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

}

