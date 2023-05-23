package com.codeplace.mvvmpokemonapp.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codeplace.mvvmpokemonapp.db.dao.PokemonsDao
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository (private val baseUrl: String, val pokemonsDao: PokemonsDao) {

         suspend fun getPokemonList() = withContext(Dispatchers.IO){
            val api = RetrofitInstance.getRetrofit(baseUrl)
            return@withContext api.getPokemonList()
        }
         suspend fun getPokemonDetails(pokemonName: String) = withContext(Dispatchers.IO){
             val api = RetrofitInstance.getRetrofit(baseUrl)
             return@withContext api.getPokemonDetails(pokemonName)
    }
        suspend fun getPokemonEffects(pokemonId:Int) = withContext(Dispatchers.IO){
            val api = RetrofitInstance.getRetrofit(baseUrl)
            return@withContext api.getPokemonEffect(pokemonId)
        }

        suspend fun getPokemonSpecies(pokemonId: Int) = withContext(Dispatchers.IO){
            val api = RetrofitInstance.getRetrofit(baseUrl)
            return@withContext api.getPokemonSpecies(pokemonId)
        }

    // db

    suspend fun getAllFavoritePokemons() = withContext(Dispatchers.IO){
        return@withContext pokemonsDao.getAll()
    }
    suspend fun addPokemonToDb(pokemonsDb:PokemonDb) = pokemonsDao.insert(pokemonsDb)
    }

