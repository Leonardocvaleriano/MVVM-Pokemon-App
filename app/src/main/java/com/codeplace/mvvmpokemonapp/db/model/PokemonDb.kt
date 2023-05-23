package com.codeplace.mvvmpokemonapp.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("pokemonTable")
class PokemonDb(
    @ColumnInfo(name = "pokemon_name")val pokemonName:String?,
    @ColumnInfo(name = "pokemon_ability") val pokemonAbility:String?,
    @ColumnInfo(name = "pokemon_type") val pokemonType:String?,
    @ColumnInfo(name = "pokemon_move")val pokemonMove:String?,
    @ColumnInfo(name = "pokemon_img_url")val pokemonImg:String?
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}

