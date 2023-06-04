package com.codeplace.mvvmpokemonapp.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.repository.PokemonRepository
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import com.codeplace.mvvmpokemonapp.ui.base.baseViewModel.BaseViewModel
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonInfos
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonNames
import com.codeplace.mvvmpokemonapp.ui.home.view.models.SynchronizedData
import kotlinx.coroutines.launch
import org.json.JSONObject

class PokemonViewModel(private val pokemonRepository: PokemonRepository) : BaseViewModel() {

    /**
     * This pokemonList of type MutableLiveData will hold the current data defined in the BaseViewModel,
     * to set it to the activity, fragment or whoever be connected with it.
     */

    // Network liveData
    val pokemonNames = MutableLiveData<StateFlow>()
    val pokemonInfo = MutableLiveData<StateFlow>()
    val pokemonFavorites = MutableLiveData<StateFlow>()
    val pokemonEffects  = MutableLiveData<StateFlow>()
    val pokemonSpecies = MutableLiveData<StateFlow>()

    // List to send to the Adapter
    val synchronizedDataList = mutableListOf<SynchronizedData>()

    // Creating the List of items
    private val pokemonNamesList =  ArrayList<PokemonNames>()
    private val pokemonInfosList = ArrayList<PokemonInfos>()
    val pokemonFavoritesList = ArrayList<PokemonDb>()

    private fun synchronizeDataInAList(){
        for (item in pokemonNamesList){
            val name = pokemonInfosList.find { it.name == item.name}!!.name
            val ability = pokemonInfosList.find { it.name == item.name}!!.ability
            val type = pokemonInfosList.find { it.name == item.name }!!.type
            val move = pokemonInfosList.find { it.name == item.name}!!.move
            val imageUrl = pokemonInfosList.find { it.name == item.name}!!.imageUrl
            val favoriteStats = pokemonFavoritesList.find { it.pokemonName == item.name}?.favoriteStats

            val synchronizedData = SynchronizedData(
                name,
                ability,
                type,
                move,
                imageUrl,
                favoriteStats
            )
            synchronizedDataList.add(synchronizedData)
        }
    }

    fun getPokemonNames() = fetchData(pokemonNames) {
        synchronizedDataList.clear()
        pokemonRepository.getPokemonNames()
    }

    fun getPokemonInfoByName() {
        pokemonNamesList.forEach {
            getPokemonInfo(it.name)
        }
    }

    // Chamado 20x
    private fun getPokemonInfo(pokemonName: String) = fetchData(pokemonInfo) {
        pokemonRepository.getPokemonInfo(pokemonName)
    }
    //        fun getPokemonDetails(pokemonId:Int) {
//
//            fun getPokemonEffects(pokemonId:Int) = fetchData(pokemonEffects){
//                pokemonRepository.getPokemonEffects(pokemonId)
//            }
//            fun getPokemonSpecies(pokemonId: Int) = fetchData(pokemonSpecies){
//                pokemonRepository.getPokemonSpecies(pokemonId)
//            }
//        }
    fun fillPokemonNamesList(result: JSONObject) {
        val resultJSONArray = result.getJSONArray("results")
        (0 until resultJSONArray.length())
            .map { resultJSONArray.getJSONObject(it) }
            .forEach {
                pokemonNamesList.add(PokemonNames(it))
            }
    }

    fun fillPokemonInfo(result: JSONObject) {
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
        pokemonInfosList.add(PokemonInfos(name,urlImage,typeName,moveName,abilityName))

        if (pokemonNamesList.size == pokemonInfosList.size){
            synchronizeDataInAList()
        }

    }
    fun getFavoritesPokemon() = getAllFavoritePokemons(pokemonFavorites){
        pokemonRepository.getAllFavoritePokemons()
    }
    fun fillFavoritePokemonList(result: List<PokemonDb>){
        result.forEach {
            pokemonFavoritesList.add(PokemonDb(it.pokemonName, it.pokemonAbility, it.pokemonType,it.pokemonMove,it.pokemonImg, it.favoriteStats))
        }
    }
    fun addPokemonToFavorites(pokemonDb: PokemonDb) = viewModelScope.launch {
        pokemonRepository.addPokemonToFavorites(pokemonDb)
    }
    fun deletePokemonFromFavorites(pokemonName:String?) = viewModelScope.launch {
        pokemonRepository.deleteFavoritePokemon(pokemonName)

    }
    fun updatePokemonFavorites(){
        getFavoritesPokemon()
        pokemonFavoritesList.clear()

    }

//        fun updateFavoritesPokemon(pokemonName: String, pokemonDb: PokemonDb) = viewModelScope.launch{
//        val isExist = pokemonRepository.checkFavPokemonByName(pokemonName).isNotEmpty()
//            if (isExist){
//                pokemonRepository.deleteFavoritePokemon(pokemonName)
//                //statusMessage.value = Event("Pokemon Removed from favorites.")
//            } else {
//                pokemonRepository.addPokemonToFavorites(pokemonDb)
//               // statusMessage.value = Event("Pokemon added to the favorites.")
//            }
//    }
}
