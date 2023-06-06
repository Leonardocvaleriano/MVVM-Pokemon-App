package com.codeplace.mvvmpokemonapp.ui.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.codeplace.mvvmpokemonapp.databinding.FragmentPokemonDetailsBinding
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import com.codeplace.mvvmpokemonapp.ui.home.viewModel.PokemonViewModel
import com.codeplace.mvvmpokemonapp.util.capitalize
import com.codeplace.mvvmpokemonapp.util.convertDecimetersToMeters
import com.codeplace.mvvmpokemonapp.util.convertHectogramsToKg
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonDetailsFragment : Fragment() {

    private var _binding: FragmentPokemonDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<PokemonViewModel>()
    private val args: PokemonDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)

        val name = args.name
        val id = args.id
        val imageUrl = args.imageUrl
        val ability = args.ability
        val type = args.type
        val move = args.move

        with(binding){
            txtPokemonName.text = name
            Glide.with(this@PokemonDetailsFragment)
                .load(imageUrl)
                .fitCenter()
                .into(imgPokemon)
            txtPokemonAbility.text = capitalize(ability)
            txtPokemonType.text = capitalize(type)
            txtPokemonMove.text = capitalize(move)
        }
        initValues(id, name)
        initObservables()

        return binding.root

    }

    private fun initValues(id: Int, name:String) {
        viewModel.getPokemonCharacteristics(id,name )
    }

    private fun initObservables() {
        viewModel.pokemonInfo.observe(viewLifecycleOwner) {
            when (it) {
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillCharacteristicsHeightWeight(it.data as JSONObject))
                is StateFlow.Error -> (errorMessage(it.errorMessage))
            }
        }

        viewModel.pokemonSpecies.observe(viewLifecycleOwner){
            when(it){
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillCharacteristicsPokemonGrowthRate(it.data as JSONObject))
                is StateFlow.Error -> (errorMessage(it.errorMessage))
            }
        }
        viewModel.pokemonSpecies.observe(viewLifecycleOwner){
            when(it){
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillCharacteristicsPokemonHabitat(it.data as JSONObject))
                is StateFlow.Error -> (errorMessage(it.errorMessage))
            }
        }
        viewModel.pokemonEffects.observe(viewLifecycleOwner) {
            when (it) {
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillCharacteristicsPokemonEffect(it.data as JSONObject))
                is StateFlow.Error -> (errorMessage(it.errorMessage))
            }
        }
    }
    private fun fillCharacteristicsHeightWeight(result: JSONObject?) {
        with(binding) {
            txtHeight.text = convertDecimetersToMeters(result!!.getInt("height").toDouble()).toString()
            txtWeight.text = convertHectogramsToKg(result.getInt("weight").toDouble()).toString()
        }
    }

    private fun fillCharacteristicsPokemonGrowthRate(result: JSONObject) {
        with(binding){
            txtGrowthRate.text = capitalize(result.getJSONObject("growth_rate").getString("name"))
        }
    }
    private fun fillCharacteristicsPokemonHabitat(result: JSONObject) {
        with(binding){
            txtHabitat.text = capitalize(result.getJSONObject("habitat").getString("name"))
        }

    }
    private fun fillCharacteristicsPokemonEffect(result: JSONObject) {
        with(binding) {
            val effectText = result.getJSONArray("effect_entries").getJSONObject(1).getString("effect").toString()
            val effectTextSpacesAdjusted = effectText.replace("\\s+".toRegex(), " ")
            txtPokemonEffect.text = effectTextSpacesAdjusted
        }
    }
    private fun errorMessage(errorMessage: String) {
        Toast.makeText(activity, errorMessage , Toast.LENGTH_SHORT).show()
    }
    private fun loading(loading: Boolean) {
        with(binding){
            progressBar.visibility = if (loading) VISIBLE else GONE
            viewBackGround.visibility = if (loading) GONE else VISIBLE
            txtPokemonName.visibility =  if (loading) GONE else VISIBLE
            imgPokemon.visibility =  if (loading) GONE else VISIBLE
            txtPokemonType.visibility =  if (loading) GONE else VISIBLE
            txtPokemonMove.visibility =  if (loading) GONE else VISIBLE
            materialCardViewPokemonEffect.visibility =  if (loading) GONE else VISIBLE
            txtPokemonAbility.visibility =  if (loading) GONE else VISIBLE
            viewPokemonCharacteristics.visibility = if (loading) GONE else VISIBLE
            txtCharacterTitle.visibility =  if (loading) GONE else VISIBLE
            txtHeightTitle.visibility =  if (loading) GONE else VISIBLE
            txtWeightTitle.visibility =  if (loading) GONE else VISIBLE
            txtHabitatTitle.visibility =  if (loading) GONE else VISIBLE
            txtGrowthRateTitle.visibility=  if (loading) GONE else VISIBLE
        }
    }


}