package com.example.xchat.activity.fragments.old

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.xchat.R
import com.example.xchat.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var hide = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.register.setOnClickListener {
            //findNavController().navigate(R.id.action_loginFragment_to_profileRFragment)
        }

        binding.passwordLogo.setOnClickListener {
            if(hide) {
                binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.passwordLogo.setImageResource(R.drawable.show_password)
                hide = false
            }else {
                binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.passwordLogo.setImageResource(R.drawable.hide_password)
                hide = true
            }
            binding.etPassword.setSelection(binding.etPassword.text.length)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}