package com.codeplace.mvvmpokemonapp.ui.home.view.models

import org.json.JSONObject
import java.io.Serializable

data class Pokemon(
    val name: String
): Serializable {
    constructor(jsonObject: JSONObject):this(
        jsonObject.getString("name")
    )
}