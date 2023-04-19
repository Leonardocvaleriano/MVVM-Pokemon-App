package com.codeplace.mvvmpokemonapp.ui.home.view.models

import org.json.JSONObject
import java.io.Serializable

data class PokemonImages(
    val name: String,
    val url:String
    ): Serializable {
    constructor(jsonObject: JSONObject):this(
        jsonObject.getJSONObject("forms").getString("name"),
        jsonObject.getJSONObject("sprites").getString("front_default")
     )
}


