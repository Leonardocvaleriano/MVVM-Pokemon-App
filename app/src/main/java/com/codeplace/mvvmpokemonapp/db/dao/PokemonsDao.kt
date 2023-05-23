package com.codeplace.mvvmpokemonapp.db.dao

import androidx.room.*
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb

@Dao
interface PokemonsDao {

        @Query("SELECT * FROM pokemonTable order by id ASC")
        fun getAll(): List<PokemonDb>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(pokemonsDb: PokemonDb)

        @Delete
        suspend fun delete(pokemonsDb: PokemonDb)
}