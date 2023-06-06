package com.codeplace.mvvmpokemonapp.repository


import com.codeplace.mvvmpokemonapp.db.dao.PokemonsDao
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository (private val baseUrl: String, val pokemonsDao: PokemonsDao) {

         suspend fun getPokemonNames() = withContext(Dispatchers.IO){
            val api = RetrofitInstance.getRetrofit(baseUrl)
            return@withContext api.getPokemonNames()
        }
         suspend fun getPokemonInfo(pokemonName: String) = withContext(Dispatchers.IO){
             val api = RetrofitInstance.getRetrofit(baseUrl)
             return@withContext api.getPokemonInfo(pokemonName)
    }
        suspend fun getPokemonEffects(id:Int) = withContext(Dispatchers.IO){
            val api = RetrofitInstance.getRetrofit(baseUrl)
            return@withContext api.getPokemonEffect(id)
        }

        suspend fun getPokemonSpecies(id: Int) = withContext(Dispatchers.IO){
            val api = RetrofitInstance.getRetrofit(baseUrl)
            return@withContext api.getPokemonSpecies(id)
        }

    // db
    suspend fun getAllFavoritePokemons() = withContext(Dispatchers.IO){
        return@withContext pokemonsDao.getAll()
    }

    suspend fun addPokemonToFavorites(pokemonsDb:PokemonDb) = withContext(Dispatchers.IO){
       return@withContext pokemonsDao.insert(pokemonsDb)
    }
    suspend fun deleteFavoritePokemon(pokemonName:String?) = withContext(Dispatchers.IO){
        return@withContext pokemonsDao.delete(pokemonName)
    }



}

