package com.codeplace.mvvmpokemonapp.ui.home.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.codeplace.mvvmpokemonapp.network.repository.PokemonRepository
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import com.codeplace.mvvmpokemonapp.ui.base.baseViewModel.BaseViewModel
import com.codeplace.mvvmpokemonapp.ui.home.view.models.Pokemon
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonDetails
import org.json.JSONObject

class PokemonViewModel(private val pokemonRepository: PokemonRepository) : BaseViewModel() {


    /**
     * This pokemonList of type MutableLiveData will hold the current data defined to the BaseViewModel,
     * to set it to the activity, fragment or whoever be connected with it.
     */
    val pokemonListNames = MutableLiveData<StateFlow>()
    val pokemonDetails = MutableLiveData<StateFlow>()
    val pokemonEffects  = MutableLiveData<StateFlow>()
    val pokemonLocation = MutableLiveData<StateFlow>()
    val pokemonHabitat = MutableLiveData<StateFlow>()

    val listPokemonNames = ArrayList<Pokemon>()
    val listPokemonDetails = ArrayList<PokemonDetails>()


    /**
     * 1. Sending the pokemonlist to the BaseViewModel, because when the API be called, the currrent data return
     * will be added to the property pokemonlist
     * 2. Sending the intended repository with the fun configured to call the API.
     */

    fun getPokemonList() = fetchData(pokemonListNames) {
        listPokemonNames.clear()
        listPokemonDetails.clear()
        pokemonRepository.getPokemonList()
    }

    fun initPokemonDetails() {
        listPokemonNames.forEach {
            getPokemonDetails(it.name)
        }
    }
    fun getPokemonDetails(pokemonName: String) = fetchData(pokemonDetails) {
        pokemonRepository.getPokemonDetails(pokemonName)
    }
    fun getPokemonEffects(pokemonId:Int) = fetchData(pokemonEffects){
        pokemonRepository.getPokemonEffects(pokemonId)
    }
    fun getPokemonLocation(pokemonId:Int) = fetchData(pokemonLocation){
        pokemonRepository.getPokemonLocation(pokemonId)
    }

    fun getPokemonHabitat(pokemonId: Int) = fetchData(pokemonHabitat){
        pokemonRepository.getPokemonHabitat(pokemonId)
    }
    fun fillListPokemonNames(result: JSONObject) {
        val resultJSONArray = result.getJSONArray("results")
        (0 until resultJSONArray.length())
            .map { resultJSONArray.getJSONObject(it) }
            .forEach {
                listPokemonNames += Pokemon(it)
            }
    }
    @SuppressLint("SuspiciousIndentation")
    fun fillListPokemonDetails(result: JSONObject) {

        val forms = result.getJSONArray("forms")
        val name = forms.getJSONObject(0).getString("name")
        val images = result.getJSONObject("sprites").getJSONObject("other").getJSONObject("home")
        val urlImage = images.getString("front_default")

        val types = result.getJSONArray("types")
        val slot = types.getJSONObject(0)
        val type = slot.getJSONObject("type")
        val typeName = type.getString("name")

        val moves = result.getJSONArray("moves")
        val move = moves.getJSONObject(0).getJSONObject("move")
        val moveName = move.getString("name")

        val abilities = result.getJSONArray("abilities")
        val ability = abilities.getJSONObject(0).getJSONObject("ability")
        val abilityName = ability.getString("name")
        listPokemonDetails.add(PokemonDetails(name,urlImage,typeName, moveName, abilityName))
    }

}