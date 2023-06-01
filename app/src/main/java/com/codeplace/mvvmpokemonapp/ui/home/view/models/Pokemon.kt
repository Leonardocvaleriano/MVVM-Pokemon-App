package com.codeplace.mvvmpokemonapp.ui.home.view.models

import org.json.JSONObject
import java.io.Serializable

data class Pokemon(
    val name: String,
    val url:String,
    var favoriteStatus:String?
): Serializable {
    constructor(jsonObject: JSONObject):this(
        jsonObject.getString("name"),
        jsonObject.getString("url"),
       jsonObject.getString("name")
    )

 }