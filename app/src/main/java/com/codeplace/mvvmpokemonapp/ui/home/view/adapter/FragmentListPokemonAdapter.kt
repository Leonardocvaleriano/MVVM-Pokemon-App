package com.codeplace.mvvmpokemonapp.ui.home.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codeplace.mvvmpokemonapp.R
import com.codeplace.mvvmpokemonapp.databinding.PokemonItemsBinding
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.ui.home.view.models.Pokemon
import com.codeplace.mvvmpokemonapp.ui.home.view.models.PokemonInfo


class FragmentListPokemonAdapter(
    private val pokemonNamesItem:List<Pokemon>,
    private val pokemonInfoItem:List<PokemonInfo>,
    private val listener: RecyclerViewClickListener,
    //private val onClickListener: (PokemonDb) -> Unit
): RecyclerView.Adapter<FragmentListPokemonAdapter.FragmentListPokemonHolder>(){


    // Hold all the views created in pokemon_items.xml
    inner class FragmentListPokemonHolder(val binding: PokemonItemsBinding): RecyclerView.ViewHolder(binding.root)

    // When scrolled, recycle all the views, changing all the content to show the others pokemon
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentListPokemonHolder {
        return FragmentListPokemonHolder(PokemonItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    // Define the quantity of items the RecyclerView will have.
    override fun getItemCount() : Int = pokemonNamesItem.size

    // Bind the data to our items that is inside the RecyclerView individually.
    override fun onBindViewHolder(holder: FragmentListPokemonHolder, position: Int) {
        // holder: We use this parameter to access the views inside of each item of our RecyclerView(viewholders)
        // position: this is the current position of the current index that we are binding here.
        if (pokemonNamesItem.size == pokemonInfoItem.size) {

            with(holder.binding) {

                    val pokemonNamesItemsPosition = pokemonNamesItem[position]
                    val pokemonImg = pokemonInfoItem.firstOrNull { it.name == pokemonNamesItemsPosition.name}!!.urlImage
                    val name = pokemonNamesItemsPosition.name
                    val move = pokemonInfoItem.firstOrNull{it.name == pokemonNamesItemsPosition.name}!!.move

                    txtPokemonName.text = pokemonNamesItemsPosition.name
                    txtPokemonMove.text = move
                    Glide.with(holder.itemView.context)
                        .load(pokemonImg)
                        .into(imgPokemon)

                    icFavorite.setOnClickListener{
                        icFavorite.setBackgroundResource(R.drawable.ic_favorite_filled)
                        }
                    }

            }

            }
        }



