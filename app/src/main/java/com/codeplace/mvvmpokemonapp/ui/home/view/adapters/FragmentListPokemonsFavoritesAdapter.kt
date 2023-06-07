package com.codeplace.mvvmpokemonapp.ui.home.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codeplace.mvvmpokemonapp.R
import com.codeplace.mvvmpokemonapp.databinding.PokemonItemsBinding
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.ui.home.view.fragments.ListFavoritesPokemonFragmentDirections
import com.codeplace.mvvmpokemonapp.ui.home.view.fragments.ListPokemonFragmentDirections
import com.codeplace.mvvmpokemonapp.util.capitalize

class FragmentListPokemonsFavoritesAdapter(
    private val pokemonFavoriteList: List<PokemonDb>,
    private val listener: PokemonFavoriteItemClickListener
) : RecyclerView.Adapter<FragmentListPokemonsFavoritesAdapter.FragmentListPokemonsFavoritesHolder>() {

    inner class FragmentListPokemonsFavoritesHolder(val binding: PokemonItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FragmentListPokemonsFavoritesHolder {
        return FragmentListPokemonsFavoritesHolder(
            PokemonItemsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = pokemonFavoriteList.size

    override fun onBindViewHolder(holder: FragmentListPokemonsFavoritesHolder, position: Int) {

        with(holder.binding) {
            val item = pokemonFavoriteList[position]
            with(item) {
                icFavorite.setImageResource(R.drawable.ic_delete)

                txtPokemonName.text = capitalize(name)
                txtPokemonAbility.text = capitalize(ability)
                txtPokemonType.text = capitalize(type)
                txtPokemonMove.text = capitalize(move)
                Glide.with(holder.itemView.context)
                    .load(imgUrl)
                    .into(imgPokemon)

                icFavorite.setOnClickListener {
                    listener.removeFromFavoriteClick(
                        name,
                        "Pokemon has been removed from favorites list."
                    )
                    icFavorite.setBackgroundResource(R.drawable.ic_favorite)
                }
                clickableTextMore.setOnClickListener {
                    val navController = findNavController(holder.itemView)
                    val action = ListFavoritesPokemonFragmentDirections.naviteToPokemonDetailsFragment(name!!,id!!, imgUrl!!,ability!!,type!!,move!!)
                    navController.navigate(action)
                }
            }
        }
    }
}
