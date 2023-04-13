package com.codeplace.mvvmpokemonapp.network.repository

import com.codeplace.mvvmpokemonapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository(private val baseUrl: String) {
        suspend fun getPokemonList(limit:Int, offset:Int) = withContext(Dispatchers.IO){
            val api = RetrofitInstance.getRetrofit(baseUrl)
            return@withContext api.getPokemonList(limit, offset)
        }
         suspend fun getPokemonDetail(id:Int) = withContext(Dispatchers.IO){
             val api = RetrofitInstance.getRetrofit(baseUrl)
             return@withContext api.getPokemonDetail(id)
    }
}