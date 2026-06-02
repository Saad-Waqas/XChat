package com.example.xchat.activity.fragments.newo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xchat.R
import com.example.xchat.activity.MainActivity
import com.example.xchat.activity.adapter.SimpleListAdapter
import com.example.xchat.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.measureBtnLayout.setOnClickListener{
            findNavController().navigate(R.id.action_home_to_measure)
        }

    }

    private fun setAdapterList(){
        /*val list = listOf("Item 1", "Item 2", "Item 3")

        binding.recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.recycler.adapter = SimpleListAdapter(list)*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}