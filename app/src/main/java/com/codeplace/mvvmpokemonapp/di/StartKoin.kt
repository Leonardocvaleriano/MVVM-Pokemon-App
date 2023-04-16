package com.codeplace.mvvmpokemonapp.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

object StartKoin {
    fun initKoin(context: Context){
        startKoin {
            androidLogger()
            androidContext(context)
            modules(appModule)
        }
    }

    fun stopKoin(){
        stopKoin()
    }
}