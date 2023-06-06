package com.codeplace.mvvmpokemonapp.db.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb

@Dao
interface PokemonsDao {

        @Query("SELECT * FROM pokemonTable order by id ASC")
        fun getAll(): List<PokemonDb>

        @Query("SELECT name FROM pokemonTable WHERE name=:name")
        fun checkFavPokemonByName(name:String?):String?

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(pokemonDb: PokemonDb)

        @Query("DELETE FROM pokemonTable WHERE name =:name")
        suspend fun delete(name:String?)
}