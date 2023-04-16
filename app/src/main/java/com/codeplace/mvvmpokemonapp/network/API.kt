package com.codeplace.mvvmpokemonapp.network

import androidx.transition.Transition.MatchOrder
import com.codeplace.mvvmpokemonapp.ui.home.view.models.Pokemons
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit")limit:Int,
        @Query("offset")offset:Int):Response<Map<String,*>>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name")name:String):Response<Map<String,*>>
}