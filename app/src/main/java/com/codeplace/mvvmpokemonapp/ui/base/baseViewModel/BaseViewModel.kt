package com.codeplace.mvvmpokemonapp.ui.base.baseViewModel

  import android.util.Log
  import androidx.lifecycle.MutableLiveData
  import androidx.lifecycle.ViewModel
  import androidx.lifecycle.viewModelScope
  import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
  import kotlinx.coroutines.launch
  import org.json.JSONObject
  import retrofit2.Response

open class BaseViewModel : ViewModel() {

    fun fetchData(
        liveData: MutableLiveData<StateFlow>,
        service: suspend () -> Response<*>) {
        viewModelScope.launch {
            liveData.value = StateFlow.Loading(true)
            // try catch below to no crash the app when there is no connection.

            try {
                val response = service()
                val jsonResponse = JSONObject(response.body()!! as Map<*,*>)
                if (response.isSuccessful) {
                    liveData.value = StateFlow.Loading(false)
                    liveData.value = StateFlow.Success(jsonResponse)
                }
                      else{
                    liveData.value = StateFlow.Error(response.errorBody()!!.toString(),null,null,null)
                }

            } catch (e: Exception) {
                Log.e("VmViewModel", Log.getStackTraceString(e))
                liveData.value = StateFlow.Loading(false)
                liveData.value = StateFlow.Error(e.message!!,null,null,null)

            }
        }
    }

//    private fun JSONObject.getIgnoreCase(key: String): String {
//        keys().forEach {
//            if (it.equals(key, true)) {
//                return getString(it)
//            }
//        }
//        return ""
//    }

    fun fetchDataPokemonDetail() {

    }
}


