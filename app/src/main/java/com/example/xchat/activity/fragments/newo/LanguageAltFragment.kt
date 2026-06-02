package com.example.xchat.activity.fragments.newo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xchat.R
import com.example.xchat.activity.adapter.LanguageAdapter
import com.example.xchat.databinding.FragmentLanguageAltBinding
import com.example.xchat.databinding.FragmentLanguageBinding
import kotlin.code

class LanguageAltFragment : Fragment() {

    private var _binding: FragmentLanguageAltBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: LanguageAdapter
    var code = "en"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLanguageAltBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setupRecyclerView()

    }

    private fun init(){
        binding.nextButton.setOnClickListener{
            findNavController().navigate(R.id.action_Alt_languageFragment_to_main)
        }
    }

    private fun setupRecyclerView() {
        adapter = LanguageAdapter(code) { selectedLanguage ->
            code = selectedLanguage.code

        }

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter

        LanguageAdapter.instanceState?.let {
            layoutManager.onRestoreInstanceState(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}