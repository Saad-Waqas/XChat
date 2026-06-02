package com.example.xchat.activity.fragments.newo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xchat.R
import com.example.xchat.databinding.FragmentOnboardingFourBinding
import com.example.xchat.databinding.FragmentSplashBinding

class OnboardingFourFragment : Fragment() {

    private var _binding: FragmentOnboardingFourBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardingFourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init(){
        binding.nextButton.setOnClickListener {
            (parentFragment as? MainOnboardingFragment)?.goToHomePage()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}