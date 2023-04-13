package com.codeplace.mvvmpokemonapp.network

import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonDetail
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonList
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