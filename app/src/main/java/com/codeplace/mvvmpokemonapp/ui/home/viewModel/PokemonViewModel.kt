package com.codeplace.mvvmpokemonapp.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.repository.PokemonRepository
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import com.codeplace.mvvmpokemonapp.ui.base.baseViewModel.BaseViewModel
import com.codeplace.mvvmpokemonapp.ui.home.view.models.Pokemon
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonInfo
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


    val allPokemonsAsFavorites = MutableLiveData<StateFlow>()
    val allPokemonsAsFavorites_= ArrayList<PokemonDb>()
    val pokemonNames_ = ArrayList<Pokemon>()
    val pokemonInfo_ = ArrayList<PokemonInfo>()

    val pokemonEffects  = MutableLiveData<StateFlow>()
    val pokemonSpecies = MutableLiveData<StateFlow>()


        fun getPokemonNames() = fetchData(pokemonNames) {
//            pokemonNames_.clear()
//            pokemonInfo_.clear()
            pokemonRepository.getPokemonNames()
        }

    fun getPokemonInfoByName() {
        pokemonNames_.forEach { pokemonNames ->
            getPokemonInfo(pokemonNames.name)
        }
    }
    fun getPokemonInfo(pokemonName: String) = fetchData(pokemonInfo) {
        pokemonRepository.getPokemonInfo(pokemonName)
    }
        fun getPokemonDetails(pokemonId:Int) {

            fun getPokemonEffects(pokemonId:Int) = fetchData(pokemonEffects){
                pokemonRepository.getPokemonEffects(pokemonId)
            }
            fun getPokemonSpecies(pokemonId: Int) = fetchData(pokemonSpecies){
                pokemonRepository.getPokemonSpecies(pokemonId)
            }
        }
    fun fillPokemonNames(result: JSONObject) {
        val resultJSONArray = result.getJSONArray("results")
        (0 until resultJSONArray.length())
            .map { resultJSONArray.getJSONObject(it) }
            .forEach {
                pokemonNames_ += Pokemon(it)
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

        pokemonInfo_.add(PokemonInfo(name, urlImage, typeName,moveName, abilityName))

    }

        fun getAllFavoritesPokemon() = getAllFavoritePokemons(allPokemonsAsFavorites){
            pokemonRepository.getAllFavoritePokemons()
    }


        fun fillListFavoritePokemons(result: List<PokemonDb>){
            result.forEach {
             allPokemonsAsFavorites_.add(PokemonDb(it.pokemonName, it.pokemonAbility, it.pokemonType,it.pokemonMove,it.pokemonImg))
        }
     }

    fun addPokemonToFavorites(pokemonDb: PokemonDb) = viewModelScope.launch {
        pokemonRepository.addPokemonToFavorites(pokemonDb)
    }

    fun deletePokemonFromFavorites(pokemonName:String) = viewModelScope.launch {
        pokemonRepository.deleteFavoritePokemon(pokemonName)
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

