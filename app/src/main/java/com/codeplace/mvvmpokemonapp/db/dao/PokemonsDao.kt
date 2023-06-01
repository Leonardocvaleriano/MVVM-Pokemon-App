package com.codeplace.mvvmpokemonapp.db.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb

@Dao
interface PokemonsDao {

        @Query("SELECT * FROM pokemonTable order by id ASC")
        fun getAll(): List<PokemonDb>

        @Query("SELECT pokemon_name FROM pokemonTable WHERE pokemon_name=:pokemonName")
        fun checkFavPokemonByName(pokemonName:String?):String?

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(pokemonsDb: PokemonDb)

        @Query("DELETE FROM pokemonTable WHERE pokemon_name = :pokemonName")
        suspend fun delete(pokemonName:String?)
}