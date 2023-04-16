package com.codeplace.mvvmpokemonapp.ui.home.view.models

import org.json.JSONObject
import java.io.Serializable

data class PokemonDetails(
    val sprites: Sprites
): Serializable {
    constructor(jsonObject: JSONObject):this(
        fillSprites(jsonObject.getJSONObject("sprites"))
    )
}

fun fillSprites(jsonObject: JSONObject): Sprites {
    return Sprites(jsonObject)
}

