package com.codeplace.mvvmpokemonapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("pokemon")
    suspend fun getPokemonNames():Response<Map<String, *>>
    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name")name:String?):Response<Map<String, *>>
    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(
        @Path("id")id:Int?):Response<Map<String, *>>
    @GET("ability/{id}")
    suspend fun getPokemonEffect(
        @Path("id")id:Int?):Response<Map<String, *>>

}