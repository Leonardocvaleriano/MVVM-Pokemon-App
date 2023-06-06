package com.codeplace.mvvmpokemonapp.ui.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codeplace.mvvmpokemonapp.R
import com.codeplace.mvvmpokemonapp.databinding.PokemonItemsBinding
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.ui.home.view.fragments.ListPokemonFragmentDirections
import com.codeplace.mvvmpokemonapp.ui.home.view.models.SynchronizedData
import com.codeplace.mvvmpokemonapp.util.capitalize

class FragmentListPokemonAdapterCustomized(
    private val synchronizedDataList: List<SynchronizedData>,
    private val listener: PokemonItemClickListener
) : RecyclerView.Adapter<FragmentListPokemonAdapterCustomized.ViewHolder>() {


    inner class ViewHolder(val binding: PokemonItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PokemonItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = synchronizedDataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = synchronizedDataList[position]
        with(holder.binding) {
            with(item) {
                txtPokemonName.text = capitalize(name)
                txtPokemonAbility.text = capitalize(ability)
                txtPokemonType.text = capitalize(type)
                txtPokemonMove.text = capitalize(move)
                Glide.with(holder.itemView.context)
                    .load(item.imageUrl)
                    .into(imgPokemon)

                if (favoriteStats != null || favoriteStats == ("1")) {
                    icFavorite.setImageResource(R.drawable.ic_favorite_filled)

                } else if (favoriteStats == ("0") || favoriteStats == null) {
                    icFavorite.setImageResource(R.drawable.ic_favorite)
                }
                icFavorite.setOnClickListener {
                    if (favoriteStats.equals("0") || favoriteStats == null) {
                        favoriteStats = "1"
                        listener.addToFavoriteClick(
                            PokemonDb(name, ability, type, move, imageUrl, favoriteStats),
                            "Pokemon has been Added to favorites list."
                        )
                        icFavorite.setImageResource(R.drawable.ic_favorite_filled)
                    } else {
                        favoriteStats = "0"
                        listener.removeFromFavoritesClick(
                            name,
                            "Pokemon has been removed from favorites list."
                        )
                        icFavorite.setImageResource(R.drawable.ic_favorite)
                    }
                }

                clickableTextMore.setOnClickListener {
                    val navController = findNavController(holder.itemView)
                    val action = ListPokemonFragmentDirections.navigateToPokemonDetailsFragment(
                        name,
                        id,
                        imageUrl,
                        ability,
                        type,
                        move
                    )
                    navController.navigate(action)
                }
            }
        }
    }
}



