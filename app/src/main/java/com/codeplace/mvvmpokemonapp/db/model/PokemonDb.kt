package com.codeplace.mvvmpokemonapp.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity("pokemonTable", indices = [Index(value =["name"], unique = true)])
class PokemonDb(
    @ColumnInfo(name = "name")val name:String?,
    @PrimaryKey(autoGenerate = false)val id:Int?,
    @ColumnInfo(name = "ability") val ability:String?,
    @ColumnInfo(name = "type") val type:String?,
    @ColumnInfo(name = "move")val move:String?,
    @ColumnInfo(name = "img_url")val imgUrl:String?,
    @ColumnInfo(name = "favorite_stats")val favoriteStats:String?

    ) {
    //@PrimaryKey(autoGenerate = true) var id = 0
}

