package com.codeplace.mvvmpokemonapp.ui.home.view.models

import org.json.JSONObject
import java.io.Serializable

data class PokemonDetails(
    val name: String,
    val urlImage:String,
    val typeName: String,
    val moveName:String

    ): Serializable {
    constructor(jsonObject: JSONObject):this(
        jsonObject.getString("name"),
        jsonObject.getString("front_default"),
        jsonObject.getString("name"),
        jsonObject.getString("name")
     )
}


