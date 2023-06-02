package com.codeplace.mvvmpokemonapp.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity("pokemonTable", indices = [Index(value =["pokemon_name"], unique = true)])
class PokemonDb(
    @ColumnInfo(name = "pokemon_name")val pokemonName:String?,
    @ColumnInfo(name = "pokemon_ability") val pokemonAbility:String?,
    @ColumnInfo(name = "pokemon_type") val pokemonType:String?,
    @ColumnInfo(name = "pokemon_move")val pokemonMove:String?,
    @ColumnInfo(name = "pokemon_img_url")val pokemonImg:String?,
    @ColumnInfo(name = "favorite_Stats")val favoriteStats:String?

    ) {
    @PrimaryKey(autoGenerate = true) var id = 0
}

