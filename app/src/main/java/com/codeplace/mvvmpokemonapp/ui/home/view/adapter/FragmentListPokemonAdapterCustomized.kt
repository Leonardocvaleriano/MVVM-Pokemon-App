package com.codeplace.mvvmpokemonapp.ui.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codeplace.mvvmpokemonapp.R
import com.codeplace.mvvmpokemonapp.databinding.PokemonItemsBinding
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.ui.home.view.models.SynchronizedData

class FragmentListPokemonAdapterCustomized(
    private val synchronizedDataList:List<SynchronizedData>,
    private val listener: RecyclerViewClickListener
) :RecyclerView.Adapter<FragmentListPokemonAdapterCustomized.ViewHolder>(){

    inner class ViewHolder(val binding: PokemonItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PokemonItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = synchronizedDataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val item = synchronizedDataList[position]

        with(holder.binding){
            txtPokemonName.text = item.name
            txtPokemonAbility.text = item.ability
            txtPokemonType.text = item.type
            txtPokemonMove.text = item.move
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgPokemon)

            if (item.favoriteStats != null && item.favoriteStats.equals("1")){
                icFavorite.setBackgroundResource(R.drawable.ic_favorite_filled)

            } else if(item.favoriteStats != null && item.favoriteStats.equals("0")){
                icFavorite.setBackgroundResource(R.drawable.ic_favorite)
            }
            icFavorite.setOnClickListener {
               if (item.favoriteStats.equals("0") || item.favoriteStats == null){
                   item.favoriteStats = "1"
                   listener.addToFavoriteClick(PokemonDb(item.name, item.ability, item.type, item.move, item.imageUrl, item.favoriteStats),
                   "Pokemon Added to the Favorites")
                   icFavorite.setBackgroundResource(R.drawable.ic_favorite_filled)
                } else {
                   item.favoriteStats = "0"
                   listener.removeFromFavoritesClick(item.name,
                   "Pokemon has been removed from favorites list.")
                   icFavorite.setBackgroundResource(R.drawable.ic_favorite)

               }
            }
        }

    }
}