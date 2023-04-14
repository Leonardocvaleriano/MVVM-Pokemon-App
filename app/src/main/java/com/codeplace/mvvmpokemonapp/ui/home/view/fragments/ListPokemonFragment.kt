package com.codeplace.mvvmpokemonapp.ui.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
 import com.codeplace.mvvmpokemonapp.R
import org.json.JSONObject

class ListPokemonFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_list_pokemon, container, false)

        initValues()
        initObservables()

        return view
    }


    private fun initValues() {
        //viewModel.getPokemonList()
    }
    private fun initObservables() {
           //Success initPokemons(data as JSONOBJECT)
    }


    private fun initPokemon(result: JSONObject) {
        mockPokemons(result)
        //initRecyclerAdapter()
    }
    private fun mockPokemons(result: JSONObject){
       // viewModel.fillPokemonListNames(result)
    }

}