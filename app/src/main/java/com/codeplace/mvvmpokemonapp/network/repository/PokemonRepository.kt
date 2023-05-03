package com.codeplace.mvvmpokemonapp.network.repository

import com.codeplace.mvvmpokemonapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository (private val baseUrl: String) {
        suspend fun getPokemonList() = withContext(Dispatchers.IO){
            val api = RetrofitInstance.getRetrofit(baseUrl)
            return@withContext api.getPokemonList()
        }
         suspend fun getPokemonDetails(name: String) = withContext(Dispatchers.IO){
             val api = RetrofitInstance.getRetrofit(baseUrl)
             return@withContext api.getPokemonDetails(name)
    }
}