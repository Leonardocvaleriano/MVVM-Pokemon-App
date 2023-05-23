package com.codeplace.mvvmpokemonapp.ui.base.baseViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response


open class BaseViewModel : ViewModel() {
    fun fetchData(
        liveData: MutableLiveData<StateFlow>, service: suspend () -> Response<*>) {

        viewModelScope.launch {
            liveData.value = StateFlow.Loading(true)
            // try catch below to no crash the app when there is no connection.
            try {
                val response = service()
                liveData.value = StateFlow.Loading(false)
                if (response.isSuccessful) {

                    val jsonResponse = JSONObject(response.body()!! as Map<*, *>)
                    liveData.value = StateFlow.Success(jsonResponse)
                } else if(response.code() == 504) {
                    liveData.value = StateFlow.Error(
                        "An Error occurred when tried to call the service, please try again.",
                        null,
                        null,
                        null
                    )
                } else {
                    liveData.value = StateFlow.Error(response.errorBody()!!.string(), null,null,null)
                }

            } catch (e: Exception) {
                Log.e("VmViewModel", Log.getStackTraceString(e))
                liveData.value = StateFlow.Loading(false)
                liveData.value = StateFlow.Error(e.message!!, null, null, null)
            }
        }
    }
    fun getAllFavoritePokemons(liveData: MutableLiveData<StateFlow>, dbCall: suspend () -> List<*>){
        liveData.value = StateFlow.Loading(true)
        viewModelScope.launch {
            try {
                liveData.value = StateFlow.Loading(false)
                val allFavoritePokemons = dbCall()
                liveData.value = StateFlow.Success(allFavoritePokemons)
            } catch (e:Exception){
                liveData.value = StateFlow.Loading(false)
                liveData.value = StateFlow.Error(e.message!!,null,null,null)
            }
        }

    }
}


