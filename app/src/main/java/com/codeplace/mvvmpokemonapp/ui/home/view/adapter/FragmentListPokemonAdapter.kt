import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codeplace.mvvmpokemonapp.databinding.PokemonItemsBinding
import com.codeplace.mvvmpokemonapp.ui.home.view.models.Pokemon
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonDetails
import com.codeplace.mvvmpokemonapp.util.capitalize

class FragmentListPokemonAdapter(
    private var listPokemonNames: List<Pokemon>,
    var listPokemonDetails: List<PokemonDetails>,
    private val listener: RecyclerViewClickListener
 ): RecyclerView.Adapter<FragmentListPokemonAdapter.FragmentListPokemonHolder>(){



    /**
     * This view holder class is used to hold the whole view of our recyclerView, which in this case contain all the views
w     * previously created inside the pokemon_items.xml
     */
    inner class FragmentListPokemonHolder(val binding: PokemonItemsBinding): RecyclerView.ViewHolder(binding.root){
    }

    /**
     * When the user scroll down the rv, it functions will recycler new items to be showed.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentListPokemonHolder {
        return FragmentListPokemonHolder(PokemonItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }



    /**
     * Defines how many items the RecyclerView have.
     */
    override fun getItemCount() : Int{
        return listPokemonNames.size

    }
    /**
     * Bind the data to our items that is inside the RecyclerView individually.
     * holder: We use this parameter to access the views inside of each item of our RecyclerView(viewholders)
     * position: this is the current position of the current index that we are binding here.
     */
    override fun onBindViewHolder(holder: FragmentListPokemonHolder, position: Int) {
      with(holder.binding){

          val urlImage = listPokemonDetails.firstOrNull(){ it.name == listPokemonNames[position].name}
          val typeName = listPokemonDetails.firstOrNull(){ it.name == listPokemonNames[position].name}
          val moveName = listPokemonDetails.firstOrNull(){it.name == listPokemonNames[position].name}

          txtPokemonName.text = capitalize(listPokemonNames[position].name)
          txtPokemonType.text = capitalize(typeName?.typeName)
          txtPokemonMove.text = capitalize(moveName?.moveName)
            Glide.
                with(holder.itemView.context)
                    .load(urlImage?.urlImage)
                    .into(imgPokemon)

          icFavoriteCard.setOnClickListener {
              listener.onRecyclerViewCardClick(icFavoriteCard, listPokemonNames[position])
          }
          }
    }

}


