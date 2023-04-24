package com.codeplace.mvvmpokemonapp.ui.home.view.activity


 import android.os.Bundle
 import android.view.LayoutInflater
 import androidx.appcompat.app.AppCompatActivity
 import androidx.navigation.findNavController
 import androidx.navigation.fragment.NavHostFragment
 import androidx.navigation.ui.setupWithNavController
 import com.codeplace.mvvmpokemonapp.R
 import com.codeplace.mvvmpokemonapp.databinding.ActivityPokemonListBinding
 import com.codeplace.mvvmpokemonapp.ui.home.view.fragments.FavoritesFragmentDirections
 import com.codeplace.mvvmpokemonapp.ui.home.view.fragments.ListPokemonFragmentDirections
 import com.google.android.material.bottomnavigation.BottomNavigationView


class PokemonListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonListBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)


        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.listPokemonFragment -> {
                     navigateToFragmentListPokemon()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.favoritesPokemonFragment -> {
                    navigateToFragmentFavorites()
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }

        binding.bottomNavigationView.setOnItemReselectedListener((BottomNavigationView.OnNavigationItemReselectedListener {
                item -> navController.popBackStack(item.getItemId(), false) }))
    }

    private fun navigateToFragmentListPokemon() {
        val navController = findNavController(R.id.fragmentContainerView)
        val action = FavoritesFragmentDirections.actionFavoritesPokemonFragmentToListPokemonFragment()
        navController.navigate(action)
    }
    private fun navigateToFragmentFavorites() {
        val navController = findNavController(R.id.fragmentContainerView)
        val action = ListPokemonFragmentDirections.actionListPokemonFragmentToFavoritesFragment()
        navController.navigate(action)
    }
}