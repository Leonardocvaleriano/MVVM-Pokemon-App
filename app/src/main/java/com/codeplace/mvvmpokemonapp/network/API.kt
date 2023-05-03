package com.codeplace.mvvmpokemonapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("pokemon")
    suspend fun getPokemonList():Response<Map<String, *>>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name")name:String):Response<Map<String, *>>
}