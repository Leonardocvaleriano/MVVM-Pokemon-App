package com.codeplace.mvvmpokemonapp.ui.home.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.codeplace.mvvmpokemonapp.network.repository.PokemonRepository
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import com.codeplace.mvvmpokemonapp.ui.base.baseViewModel.BaseViewModel
import com.codeplace.mvvmpokemonapp.ui.home.view.models.Pokemon
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonImages
import org.json.JSONObject

class PokemonViewModel(private val pokemonRepository: PokemonRepository) : BaseViewModel() {

    /**
     * This pokemonList of type MutableLiveData will hold the current data defined to the BaseViewModel,
     * to set it to the activity, fragment or whoever be connected with it.
     */
    val pokemonListNames = MutableLiveData<StateFlow>()
    val pokemonDetails = MutableLiveData<StateFlow>()

    val listPokemonNames = ArrayList<Pokemon>()
    val listPokemonImages = ArrayList<PokemonImages>()


    /**
     * 1. Sending the pokemonlist to the BaseViewModel, because when the API be called, the currrent data return
     * will be added to the property pokemonlist
     * 2. Sending the intended repository with the fun configured to call the API.
     */
    fun getPokemonList() = fetchData(pokemonListNames) {
        listPokemonNames.clear()
        listPokemonImages.clear()
        pokemonRepository.getPokemonList()
    }
     fun fillListPokemonNames(result: JSONObject) {
        val resultJSONArray = result.getJSONArray("results")
        (0 until resultJSONArray.length())
            .map { resultJSONArray.getJSONObject(it) }
            .forEach {
                listPokemonNames += Pokemon(it)
            }
    }

    fun initPokemonImages() {
        listPokemonNames.forEach {
            getPokemonImage(it.name)
        }
    }

    private fun getPokemonImage(name: String) = fetchData(pokemonDetails) {
        pokemonRepository.getPokemonImage(name)
    }

    @SuppressLint("SuspiciousIndentation")
    fun fillListPokemonImages(result: JSONObject) {

        val images = result.getJSONObject("sprites")
        val forms = result.getJSONArray("forms")

        val name = forms.getJSONObject(0).getString("name")
        val url = images.getString("front_default")

            listPokemonImages.add(PokemonImages(name,url))
    }
}

