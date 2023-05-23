package com.codeplace.mvvmpokemonapp.ui.home.view.models

import org.json.JSONObject
import java.io.Serializable

data class PokemonDetails(
    val name: String,
    val urlImage:String,
    val type: String,
    val move:String,
    val ability:String

    ): Serializable {
    constructor(jsonObject: JSONObject):this(
        jsonObject.getString("name"),
        jsonObject.getString("front_default"),
        jsonObject.getString("name"),
        jsonObject.getString("name"),
        jsonObject.getString("name")

        )
}


