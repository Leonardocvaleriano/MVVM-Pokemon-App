package com.codeplace.mvvmpokemonapp.stateFlow


// The purpose of this sealed interface is to enable exhaustive when statement in code, that uses the sealed class hierarchy.
sealed interface StateFlow{

    data class Loading(val loading:Boolean):StateFlow
    data class Success<T>(val data: T): StateFlow
    data class Error(val errorMessage:String, val errorCode:String, val detail:String?, val errorId:String?)
}
