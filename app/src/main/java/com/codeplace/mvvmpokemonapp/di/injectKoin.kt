package com.codeplace.mvvmpokemonapp.di

import com.codeplace.mvvmpokemonapp.PokemonApplication.Companion.BASE_URL
import com.codeplace.mvvmpokemonapp.network.repository.PokemonRepository
import com.codeplace.mvvmpokemonapp.ui.home.viewModel.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    single {PokemonRepository(BASE_URL) }
    viewModel { PokemonViewModel(get())}

}