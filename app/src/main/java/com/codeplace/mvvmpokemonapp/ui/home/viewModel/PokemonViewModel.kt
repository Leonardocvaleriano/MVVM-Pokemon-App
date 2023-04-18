package com.codeplace.mvvmpokemonapp.ui.home.viewModel

import android.annotation.SuppressLint
import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import com.codeplace.mvvmpokemonapp.network.repository.PokemonRepository
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import com.codeplace.mvvmpokemonapp.ui.base.baseViewModel.BaseViewModel
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonImages
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonName

import org.json.JSONObject

class PokemonViewModel(val pokemonRepository: PokemonRepository) : BaseViewModel() {

    /**
     * This pokemonList of type MutableLiveData will hold the current data defined to the BaseViewModel,
     * to set it to the activity, fragment or whoever be connected with it.
     */
    val pokemonListNames = MutableLiveData<StateFlow>()
    val pokemonImage = MutableLiveData<StateFlow>()

     val listPokemonNames = ArrayList<PokemonName>()
     val listPokemonImages = ArrayList<PokemonImages>()


    /**
     * 1. Sending the pokemonlist to the BaseViewModel, because when the API be called, the currrent data return
     * will be added to the property pokemonlist
     * 2. Sending the intended repository with the fun configured to call the API.
     */
    fun getPokemonList() = fetchData(pokemonListNames){
        pokemonRepository.getPokemonList()
    }

    fun initPokemonImages(){
        listPokemonNames.forEach {
            getPokemonImage(it.name)
        }
    }
    fun getPokemonImage(name: String) = fetchData(pokemonImage){
        pokemonRepository.getPokemonImage(name)
    }

    fun fillListPokemonNames(result:JSONObject){
        val resultJSONArray = result.getJSONArray("results")
        (0 until resultJSONArray.length())
            .map { resultJSONArray.getJSONObject(it) }
            .forEach{listPokemonNames += PokemonName(it)
            }
    }
    @SuppressLint("SuspiciousIndentation")
    fun fillListPokemonImage(result:JSONObject){
        val sprites = result.getJSONObject("sprites")
        val forms = result.getJSONArray("forms")
        val url = sprites.getString("front_default")
        val name = forms.getJSONObject(0).getString("name")
        listPokemonImages.add(PokemonImages(name, url))
    }
}


