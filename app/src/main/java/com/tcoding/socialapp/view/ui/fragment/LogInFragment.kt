package com.tcoding.socialapp.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tcoding.socialapp.R
import com.tcoding.socialapp.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {

    lateinit var binding: FragmentLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLogInBinding.inflate(inflater, container, false)


        binding.btnCreateAccount.setOnClickListener {
            createAccount()
        }

        return binding.root
    }

    fun createAccount() {
        findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
    }

}