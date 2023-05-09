package com.codeplace.mvvmpokemonapp.ui.home.view.activity


 import android.os.Bundle
 import android.view.LayoutInflater
 import androidx.appcompat.app.AppCompatActivity
 import androidx.navigation.fragment.NavHostFragment
 import androidx.navigation.ui.setupWithNavController
 import com.codeplace.mvvmpokemonapp.PokemonNavGraphDirections
 import com.codeplace.mvvmpokemonapp.R
 import com.codeplace.mvvmpokemonapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)
        initListeners()
    }

    private fun initListeners() {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.listPokemonFragment -> {
                     val action = PokemonNavGraphDirections.globalActionNavigateToNestedGraph()
                    navController.navigate(action)
                    return@setOnNavigationItemSelectedListener true
                 }
                R.id.ListFavoritesPokemonFragment -> {
                     val action = PokemonNavGraphDirections.globalActionNavigateToFavoritesFragment()
                    navController.navigate(action)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }
}
