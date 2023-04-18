
import android.view.View
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonName

interface RecyclerViewClickListener {
    // view parameters, because we need to detect what view was clicked.
    fun onRecyclerViewCardClick(view: View, pokemon:PokemonName )

}