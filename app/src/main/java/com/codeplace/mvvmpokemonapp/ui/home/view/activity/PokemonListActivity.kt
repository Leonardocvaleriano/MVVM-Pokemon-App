package com.codeplace.mvvmpokemonapp.ui.home.view.activity


import RecyclerViewClickListener
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.codeplace.mvvmpokemonapp.R
import com.codeplace.mvvmpokemonapp.databinding.ActivityPokemonListBinding
import com.codeplace.mvvmpokemonapp.ui.home.viewModel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PokemonListActivity : AppCompatActivity() {

    private lateinit var navController:NavController
    private lateinit var binding: ActivityPokemonListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonListBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}