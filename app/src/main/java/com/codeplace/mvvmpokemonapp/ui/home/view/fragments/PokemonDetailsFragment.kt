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
import com.codeplace.mvvmpokemonapp.databinding.FragmentPokemonDetailsBinding
import com.codeplace.mvvmpokemonapp.stateFlow.StateFlow
import com.codeplace.mvvmpokemonapp.ui.home.viewModel.PokemonViewModel
import com.codeplace.mvvmpokemonapp.util.capitalize
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailsBinding
    private val viewModel by viewModel<PokemonViewModel>()
    private val args: PokemonDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)

        val pokemonName = args.pokemonName
        val pokemonUrl = args.pokemonUrl.replace("https://pokeapi.co/api/v2/pokemon/", "")
        val pokemonId = pokemonUrl.replace("/", "").toInt()

        bindingPokemonDetailsFromPokemonList()
        initValues(pokemonName, pokemonId)
        initObservables()
        return binding.root
    }

    private fun bindingPokemonDetailsFromPokemonList() {
        with(binding) {
            txtPokemonName.text = capitalize(args.pokemonName)
            com.bumptech.glide.Glide.with(this@PokemonDetailsFragment)
                .load(args.urlPokemonImage)
                .fitCenter()
                .into(imgPokemon)
            txtPokemonName.text = capitalize(args.pokemonName)
            txtPokemonAbility.text = capitalize(args.pokemonAbilityName)
            txtPokemonType.text = capitalize(args.pokemonTypeName)
            txtPokemonMove.text = capitalize(args.pokemonMoveName)
        }
    }

    private fun initValues(pokemonName: String, pokemonId: Int) {
        viewModel.getPokemonDetails(pokemonName)
        viewModel.getPokemonLocation(pokemonId)
        viewModel.getPokemonHabitat(pokemonId)
        viewModel.getPokemonEffects(pokemonId)

    }

    private fun initObservables() {
        viewModel.pokemonDetails.observe(viewLifecycleOwner) {
            when (it) {
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillCharacteristicsHeightWeight(it.data as JSONObject))
                is StateFlow.Error -> (errorMessage(it.errorMessage))
            }
        }

        viewModel.pokemonLocation.observe(viewLifecycleOwner){
            when(it){
                is StateFlow.Loading -> (loading(it.loading))
                is StateFlow.Success<*> -> (fillCharacteristicsPokemonLocation(it.data as JSONObject))
                is StateFlow.Error -> (errorMessage(it.errorMessage))
            }
        }
        viewModel.pokemonHabitat.observe(viewLifecycleOwner){
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
            txtHeight.text = result?.getInt("height").toString()
            txtWeight.text = result?.getInt("weight").toString()
        }
    }

    private fun fillCharacteristicsPokemonLocation(result: JSONObject) {
        with(binding){
            txtLocation.text = capitalize(result.getString("name"))
        }
    }
    private fun fillCharacteristicsPokemonHabitat(result: JSONObject) {
        with(binding){
            txtHabitat.text = capitalize(result.getString("name"))

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
        binding.progressBar.visibility = if (loading) VISIBLE else GONE
    }
}