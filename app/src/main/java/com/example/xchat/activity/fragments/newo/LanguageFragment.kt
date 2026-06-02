package com.example.xchat.activity.fragments.newo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xchat.R
import com.example.xchat.activity.adapter.LanguageAdapter
import com.example.xchat.databinding.FragmentLanguageBinding

class LanguageFragment : Fragment() {

    private var _binding: FragmentLanguageBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: LanguageAdapter
    var code = "en"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        val fromSettingL = arguments?.getBoolean("fromSettingL") ?: false

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.layoutManager = layoutManager
        adapter = LanguageAdapter(code,fromSettingL) { selectedLanguage ->
            code = selectedLanguage.code
            LanguageAdapter.instanceState = layoutManager.onSaveInstanceState()

            findNavController().navigate(R.id.action_languageFragment_to_Alt)

        }

        binding.recycler.adapter = adapter

        // Set initial code and button visibility
        adapter.selectedLanguage?.let {
            code = it.code
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}