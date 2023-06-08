package com.codeplace.mvvmpokemonapp.ui.home.view.activity


 import android.os.Bundle
 import android.view.LayoutInflater
 import android.widget.Toast
 import androidx.appcompat.app.AppCompatActivity
 import androidx.core.app.PendingIntentCompat.getActivity
 import androidx.navigation.NavController
 import androidx.navigation.findNavController
 import androidx.navigation.fragment.NavHostFragment
 import androidx.navigation.ui.AppBarConfiguration
 import androidx.navigation.ui.navigateUp
 import androidx.navigation.ui.setupActionBarWithNavController
 import androidx.navigation.ui.setupWithNavController
 import com.codeplace.mvvmpokemonapp.PokemonNavGraphDirections
 import com.codeplace.mvvmpokemonapp.R
 import com.codeplace.mvvmpokemonapp.databinding.ActivityMainBinding
 import com.codeplace.mvvmpokemonapp.ui.home.view.adapters.onBackPressedClickListener

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)
        initNavigation()
    }

    private fun initNavigation() {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        setupActionBarWithNavController(navController, appBarConfiguration)

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
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()

    }

}
