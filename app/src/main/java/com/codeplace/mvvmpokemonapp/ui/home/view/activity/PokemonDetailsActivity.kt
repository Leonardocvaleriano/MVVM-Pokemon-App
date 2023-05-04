package com.codeplace.mvvmpokemonapp.ui.home.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codeplace.mvvmpokemonapp.R
import com.codeplace.mvvmpokemonapp.databinding.ActivityPokemonDetailsBinding

class PokemonDetailsActivity : AppCompatActivity() {

    lateinit var binding:ActivityPokemonDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}