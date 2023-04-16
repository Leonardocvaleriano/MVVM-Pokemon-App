package com.codeplace.mvvmpokemonapp.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.codeplace.mvvmpokemonapp.network.repository.PokemonRepository
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import com.codeplace.mvvmpokemonapp.ui.base.baseViewModel.BaseViewModel
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonDetails
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonName
import com.codeplace.mvvmpokemonapp.ui.home.view.models.Sprites
import com.codeplace.mvvmpokemonapp.util.Constants.PAGE_SIZE
import org.json.JSONObject

class PokemonViewModel(val pokemonRepository: PokemonRepository) : BaseViewModel() {

    val curPage = 0

    /**
     * This pokemonList of type MutableLiveData will hold the current data defined to the BaseViewModel,
     * to set it to the activity, fragment or whoever be connected with it.
     */

    val pokemonList = MutableLiveData<StateFlow>()
    val pokemonDetailList = MutableLiveData<StateFlow>()

     val listPokemonNames = ArrayList<PokemonName>()
     val listPokemonDetails = ArrayList<PokemonDetails>()


    /**
     * 1. Sending the pokemonlist to the BaseViewModel, because when the API be called, the currrent data return
     * will be added to the property pokemonlist
     * 2. Sending the intended repository with the fun configured to call the API.
     */
    fun getPokemonList() = fetchData(pokemonList){
        pokemonRepository.getPokemonList(PAGE_SIZE, PAGE_SIZE * curPage)
    }

    fun initPokemonDetailsList(){
        listPokemonNames.forEach {
            getPokemonDetails(it.name)
        }
    }
    fun getPokemonDetails(name: String) = fetchData(pokemonDetailList){
        pokemonRepository.getPokemonDetails(name)
    }

    fun fillListPokemonNames(result:JSONObject){
        val resultJSONArray = result.getJSONArray("results")
        (0 until resultJSONArray.length())
            .map { resultJSONArray.getJSONObject(it) }
            .forEach{listPokemonNames += PokemonName(it)}
    }

    fun fillListPokemonDetails(result:JSONObject){
        val pokemonDetails = PokemonDetails(result)
        listPokemonDetails.add(pokemonDetails)
    }
}