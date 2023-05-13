package com.codeplace.mvvmpokemonapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("pokemon")
    suspend fun getPokemonList():Response<Map<String, *>>
    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetails(
        @Path("pokemonName")pokemonName:String):Response<Map<String, *>>
    @GET("ability/{pokemonId}")
    suspend fun getPokemonEffect(
        @Path("pokemonId")pokemonId:Int):Response<Map<String, *>>
    @GET("location/{pokemonId}")
    suspend fun getPokemonLocation(
        @Path("pokemonId")pokemonId:Int):Response<Map<String, *>>
    @GET("pokemon-habitat/{pokemonId}")
    suspend fun getPokemonHabitat(
        @Path("pokemonId")pokemonId:Int):Response<Map<String, *>>

}