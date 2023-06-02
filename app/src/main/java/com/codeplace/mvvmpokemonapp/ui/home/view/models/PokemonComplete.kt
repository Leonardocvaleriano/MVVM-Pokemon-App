package com.codeplace.mvvmpokemonapp.ui.home.view.models

import org.json.JSONObject
import java.io.Serializable

open class PokemonComplete

class PokemonNames(val name:String,
                   val favoriteStats:String,
                   val url:String?):PokemonComplete(), Serializable {
    constructor(jsonObject: JSONObject):this(
        jsonObject.getString("name"),
        jsonObject.getString("name"),
        jsonObject.getString("url"))
}

class PokemonInfos(
    val name: String,
    val imageUrl:String,
    val type: String,
    val move:String,
    val ability:String):PokemonComplete()









