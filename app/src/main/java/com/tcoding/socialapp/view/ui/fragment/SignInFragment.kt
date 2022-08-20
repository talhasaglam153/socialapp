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
import com.tcoding.socialapp.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            signIn()
        }
        binding.tvSignUp.setOnClickListener {
            toSignUpScreen()
        }
        return binding.root
    }

    fun signIn() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if(!email.equals("") || !password.equals("")) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        findNavController().navigate(R.id.action_signInFragment_to_homePageFragment)
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_SHORT).show()
                }
        }else {
            Toast.makeText(requireContext(), "Lütfen tüm alanları doldurduğunuza emin olun", Toast.LENGTH_SHORT).show()
        }
    }

    fun toSignUpScreen() {
        findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
    }



}