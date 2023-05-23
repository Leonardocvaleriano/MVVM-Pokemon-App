package com.codeplace.mvvmpokemonapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codeplace.mvvmpokemonapp.db.dao.PokemonsDao
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb


@Database(entities = [PokemonDb::class], version = 1)
abstract class PokemonDataBase:RoomDatabase() {
        abstract fun pokemonDao(): PokemonsDao
        companion object {
                // Singleton prevents multiple
                // instances of database opening at the
                // same time.
                @Volatile
                private var INSTANCE: PokemonDataBase? = null

                fun getDatabase(context: Context): PokemonDataBase {
                        // if the INSTANCE is not null, then return it,
                        // if it is, then create the database
                        return INSTANCE ?: synchronized(this) {
                                val instance = Room.databaseBuilder(
                                        context.applicationContext,
                                        PokemonDataBase::class.java,
                                        "pokemonTable"
                                ).build()
                                INSTANCE = instance
                                // return instance
                                instance
                        }
                }
        }
}

