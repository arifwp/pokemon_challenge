package com.example.pokemon_challenge.ui

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.annotation.RawRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieListener
import com.airbnb.lottie.LottieOnCompositionLoadedListener
import com.example.pokemon_challenge.R
import com.example.pokemon_challenge.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animationView = binding.animationView
        animationView.playAnimation()
        Handler(Looper.getMainLooper()).postDelayed({
            if(findNavController().currentDestination?.id == R.id.splash_screen_fragment){
                findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToNavigationHome())
            }
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}