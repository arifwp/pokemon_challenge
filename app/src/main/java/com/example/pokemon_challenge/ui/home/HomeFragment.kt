package com.example.pokemon_challenge.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon_challenge.R
import com.example.pokemon_challenge.adapter.PokemonAdapter
import com.example.pokemon_challenge.databinding.FragmentHomeBinding
import com.example.pokemon_challenge.response.PokemonResultsItem
import com.example.pokemon_challenge.utils.BaseResponse
import com.example.pokemon_challenge.utils.onClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), onClick {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var pokemonAdapter: PokemonAdapter
    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateRv()
        homeViewModel.getListPokemon("15")
        observe()
    }

    private fun observe() {
        homeViewModel.listPokemon.observe(viewLifecycleOwner){
            when(it){
                is BaseResponse.Success -> {
                    pokemonAdapter.setPokemonAdapter(it.data?.results!!)
                }
                is BaseResponse.Error -> {
                    Log.d(TAG, "observe: ${it.msg.toString()}")
                    Toast.makeText(context,it.msg.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initiateRv() {
        val recyclerViewPokemon: RecyclerView = binding.rvPokemon
        pokemonAdapter = PokemonAdapter(R.id.navigation_home ,ArrayList())
        pokemonAdapter.listenerOnClick = this
        recyclerViewPokemon.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            adapter = pokemonAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickItem(pokemonId: String, pokemonName: String, pokemonUrl: String) {
        if(findNavController()?.currentDestination?.id == R.id.navigation_home){
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToDetailFragment(pokemonId, pokemonName, pokemonUrl))
        }
    }

}