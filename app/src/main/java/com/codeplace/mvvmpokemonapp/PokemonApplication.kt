package com.codeplace.mvvmpokemonapp

import android.app.Application

class PokemonApplication:Application() {

    // This is a companion object because it contain doesn't need to instanced.
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }



}