package com.tcoding.socialapp.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tcoding.socialapp.R
import com.tcoding.socialapp.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {

    lateinit var binding: FragmentLogInBinding
    private lateinit  var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLogInBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        if(auth.currentUser != null) {
            findNavController().navigate(R.id.action_logInFragment_to_homePageFragment)
        }

        binding.btnCreateAccount.setOnClickListener {
            createAccount()
        }

        binding.tvLogin.setOnClickListener {
            toSignInScreen()
        }
        return binding.root
    }

    fun toSignInScreen() {
        findNavController().navigate(R.id.action_logInFragment_to_signInFragment)
    }

    fun createAccount() {
        findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
    }

}