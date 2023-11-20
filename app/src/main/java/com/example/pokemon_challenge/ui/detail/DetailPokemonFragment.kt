package com.example.pokemon_challenge.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokemon_challenge.R
import com.example.pokemon_challenge.databinding.FragmentDetailPokemonBinding
import com.example.pokemon_challenge.utils.BaseResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPokemonFragment : Fragment() {

    private var _binding: FragmentDetailPokemonBinding? = null
    private val binding get() = _binding!!
    private val detailPokemonViewModel: DetailPokemonViewModel by viewModels()
    private val apiCustomViewModel: ApiCustomViewModel by viewModels()
    private val args: DetailPokemonFragmentArgs by navArgs()
    private val TAG = "DetailPokemonFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailPokemonBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailPokemonViewModel.getMoves(args.pokemonId.toString())
        detailPokemonViewModel.getTypes(args.pokemonId.toString())
        observe()

        binding.tvNamePokemonDetail.text = args.pokemonName
        Glide.with(this)
            .load(args.pokemonImg)
            .placeholder(R.drawable.example_img_pokemon)
            .into(binding.ivPokemonDetail)

        binding.fab.setOnClickListener {
            apiCustomViewModel.catchingPokemon(args.pokemonId.toString(), args.pokemonName.toString())
        }
    }

    private fun observe() {
        detailPokemonViewModel.dataMoves.observe(viewLifecycleOwner){
            when(it) {
                is BaseResponse.Success -> {
                    binding.tvMove.text = it.data?.name
                }

                is BaseResponse.Error -> {
                    binding.tvTypes.text = "Unknown"
                    Toast.makeText(context,it.msg.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

        detailPokemonViewModel.dataTypes.observe(viewLifecycleOwner) {
            when(it) {
                is BaseResponse.Success -> {
                    binding.tvTypes.text = it.data?.name
                }
                is BaseResponse.Error -> {
                    binding.tvTypes.text = "Unknown"
                    Toast.makeText(context,it.msg.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

        apiCustomViewModel.catchPokemon.observe(viewLifecycleOwner){
            when(it){
                is BaseResponse.Success -> {
                    if(it.data?.newName != null){
                        apiCustomViewModel.savePokemon(
                            args.pokemonId.toString(),
                            args.pokemonName.toString(),
                            args.pokemonImg.toString()
                        )
                    } else {
                        Toast.makeText(context, "Probability under 50%", Toast.LENGTH_SHORT).show()
                    }
                }
                is BaseResponse.Error -> {
                    Toast.makeText(context,it.msg.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

        apiCustomViewModel.insertPokemon.observe(viewLifecycleOwner){
            when(it){
                is BaseResponse.Success -> {
                    Toast.makeText(context,it.data?.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is BaseResponse.Error -> {
                    Toast.makeText(context,it.msg.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}