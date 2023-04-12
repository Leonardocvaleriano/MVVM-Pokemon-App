package com.codeplace.mvvmpokemonapp.network

import PokemonList
import com.codeplace.mvvmpokemonapp.ui.home.view.activity.models.PokemonDetail
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    suspend fun getPokemonList(
        @Query("limit")limit:Int,
        @Query("offset")offset:Int):Response<PokemonList>

    suspend fun getPokemonDetail(
        @Path("id")id:Int):Response<PokemonDetail>
}