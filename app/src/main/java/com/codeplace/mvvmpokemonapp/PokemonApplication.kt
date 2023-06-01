package com.codeplace.mvvmpokemonapp

import android.app.Application
import com.codeplace.mvvmpokemonapp.di.StartKoin


class PokemonApplication:Application() {
    var  appcontext:android.content.Context? = null


    override fun onCreate() {
        super.onCreate()
        appcontext = this
        StartKoin.initKoin(this)
    }


    // This is a companion object because it contain doesn't need to instanced.
    companion object {
         const val BASE_URL = "https://pokeapi.co/api/v2/"
    }



}