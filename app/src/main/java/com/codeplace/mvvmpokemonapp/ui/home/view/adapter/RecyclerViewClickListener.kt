
import android.view.View
import com.codeplace.mvvmpokemonapp.ui.home.view.models.Pokemon
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonDetails

interface RecyclerViewClickListener {
    // view parameters, because we need to detect what view was clicked.
    fun onRecyclerViewIcFavoriteCard(view: View, pokemon:PokemonDetails )
    fun onRecyclerViewIcMoreInfo(view: View, pokemon:PokemonDetails)

}