package com.codeplace.mvvmpokemonapp.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.repository.PokemonRepository
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import com.codeplace.mvvmpokemonapp.ui.base.baseViewModel.BaseViewModel
import com.codeplace.mvvmpokemonapp.ui.home.view.models.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.koin.core.KoinApplication.Companion.init

class PokemonViewModel(private val pokemonRepository: PokemonRepository) : BaseViewModel() {

    // Network liveData
    val pokemonNames = MutableLiveData<StateFlow>()
    val pokemonInfo = MutableLiveData<StateFlow>()
    val pokemonFavorites = MutableLiveData<StateFlow>()
    val pokemonEffects  = MutableLiveData<StateFlow>()
    val pokemonSpecies = MutableLiveData<StateFlow>()

    // Creating the List of items
    private val pokemonNamesList =  ArrayList<PokemonName>()
    private val pokemonInfosList = ArrayList<PokemonInfo>()
    val pokemonFavoritesList = ArrayList<PokemonDb>()

    // List to be sent to Adapter
    val synchronizedDataList = mutableListOf<SynchronizedData>()



    private fun synchronizeDataInAList(){
        for (item in pokemonNamesList){
            val name = pokemonInfosList.find { it.name == item.name}!!.name
            val ability = pokemonInfosList.find { it.name == item.name}!!.ability
            val type = pokemonInfosList.find { it.name == item.name }!!.type
            val move = pokemonInfosList.find { it.name == item.name}!!.move
            val imageUrl = pokemonInfosList.find { it.name == item.name}!!.imageUrl
            val favoriteStats = pokemonFavoritesList.find { it.name == item.name}?.favoriteStats
            val id = pokemonInfosList.find { it.name == item.name }!!.id

            val synchronizedData = SynchronizedData(
                name,
                id,
                ability,
                type,
                move,
                imageUrl,
                favoriteStats,
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
            fun getPokemonCharacteristics(id:Int, name:String) {
                getPokemonInfo(name)
                getPokemonEffects(id)
                getPokemonSpecies(id)
        }

    private fun getPokemonInfo(name: String?) = fetchData(pokemonInfo) {
        pokemonRepository.getPokemonInfo(name)
    }

    fun getPokemonEffects(id:Int) = fetchData(pokemonEffects){
        pokemonRepository.getPokemonEffects(id)
    }
    fun getPokemonSpecies(id: Int) = fetchData(pokemonSpecies){
        pokemonRepository.getPokemonSpecies(id)
    }

    fun getFavoritesPokemon() = getAllFavoritePokemons(pokemonFavorites){
        pokemonRepository.getAllFavoritePokemons()
    }

    fun fillPokemonNamesList(result: JSONObject) {
        val resultJSONArray = result.getJSONArray("results")
        (0 until resultJSONArray.length())
            .map { resultJSONArray.getJSONObject(it) }
            .forEach {
                pokemonNamesList.add(PokemonName(it))
            }
    }

    fun fillPokemonInfo(result: JSONObject) {
        val forms = result.getJSONArray("forms")
        val name = forms.getJSONObject(0).getString("name")
        val images = result.getJSONObject("sprites").getJSONObject("other").getJSONObject("home")
        val imageUrl = images.getString("front_default")

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
        val id = result.getInt("id")
        pokemonInfosList.add(PokemonInfo(name,id,imageUrl,abilityName,typeName,moveName))

        if (pokemonNamesList.size == pokemonInfosList.size){
            synchronizeDataInAList()
        }

    }

    fun fillFavoritePokemonList(result: List<PokemonDb>?){
        result?.forEach {
            pokemonFavoritesList.add(PokemonDb(it.name,it.id, it.ability, it.type,it.move,it.imgUrl, it.favoriteStats))
        }
    }
    fun addPokemonToFavorites(pokemonDb: PokemonDb) = viewModelScope.launch {
        pokemonRepository.addPokemonToFavorites(pokemonDb)
    }
    fun deletePokemonFromFavorites(name:String?) = viewModelScope.launch {
        pokemonRepository.deleteFavoritePokemon(name)
        updatePokemonFavorites()

    }
    private fun updatePokemonFavorites(){
        pokemonFavoritesList.clear()
        getFavoritesPokemon()

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
