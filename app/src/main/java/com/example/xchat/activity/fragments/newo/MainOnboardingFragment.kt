package com.example.xchat.activity.fragments.newo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.xchat.R
import com.example.xchat.databinding.FragmentLanguageBinding
import com.example.xchat.databinding.FragmentMainOnboardingBinding
import kotlin.collections.plusAssign
import kotlin.text.compareTo

class MainOnboardingFragment : Fragment() {

    private var _binding: FragmentMainOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPager()
        goToNextPage()

    }

    private fun setUpPager(){
        val fragments = listOf(
            OnboardingOneFragment(),
            OnboardingTwoFragment(),
            OnboardingFourFragment()
        )

        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = fragments.size
            override fun createFragment(position: Int) = fragments[position]
        }
    }

    fun goToNextPage() {
        val adapter = binding.viewPager.adapter ?: return
        if (binding.viewPager.currentItem < adapter.itemCount - 1) {
            binding.viewPager.currentItem += 1
        }
    }

    fun goToHomePage() {
        findNavController().navigate(R.id.action_Onboarding_to_home)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}