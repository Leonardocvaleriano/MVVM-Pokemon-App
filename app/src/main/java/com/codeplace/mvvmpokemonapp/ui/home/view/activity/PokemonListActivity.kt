package com.codeplace.mvvmpokemonapp.ui.home.view.activity


import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.codeplace.mvvmpokemonapp.R
import com.codeplace.mvvmpokemonapp.databinding.ActivityPokemonListBinding


class PokemonListActivity : AppCompatActivity() {

    private lateinit var navController:NavController
    private lateinit var binding: ActivityPokemonListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonListBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initListeners()
    }


    private fun initListeners() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)    }
}
