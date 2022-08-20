package com.tcoding.socialapp.view.ui.fragment

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
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
import com.tcoding.socialapp.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        auth = Firebase.auth

        binding.ivVisibility.setOnClickListener {
           icVisibleOnClickListener()
        }
        binding.ivVisibility2.setOnClickListener {
            icInvisibleOnClickListener()
        }

        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.btnContinue.setOnClickListener {
            signUp()
        }

        return binding.root

    }

    fun signUp() {
        val email = binding.etMail.text.toString()
        val password = binding.etPassword.text.toString()
        val passwordAgain = binding.etPasswordAgain.text.toString()
        if(!email.equals("") || !password.equals("") || !passwordAgain.equals("")) {
            if(password.equals(passwordAgain)) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful) {
                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_signUpFragment_to_homePageFragment)
                        }
                    }.addOnFailureListener { exception ->
                        Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
            }else {
                Toast.makeText(requireContext(), "Sure password areas same", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(requireContext(), "To be sure all fields are full", Toast.LENGTH_SHORT).show()
        }

    }
    fun icVisibleOnClickListener() {
        if(binding.etPassword.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.etPasswordAgain.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.etPassword.setSelection(binding.etPassword.text.length)
            binding.etPasswordAgain.setSelection(binding.etPasswordAgain.text.length)
            binding.ivVisibility.setImageResource(R.drawable.ic_visible)
            binding.ivVisibility2.setImageResource(R.drawable.ic_visible)
        }else {
            binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.etPasswordAgain.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.ivVisibility.setImageResource(R.drawable.ic_invisibility)
            binding.etPassword.setSelection(binding.etPassword.text.length)
            binding.etPasswordAgain.setSelection(binding.etPasswordAgain.text.length)
            binding.ivVisibility2.setImageResource(R.drawable.ic_invisibility)
        }
    }
    fun icInvisibleOnClickListener() {
        if(binding.etPassword.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.etPasswordAgain.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.etPassword.setSelection(binding.etPassword.text.length)
            binding.etPasswordAgain.setSelection(binding.etPasswordAgain.text.length)
            binding.ivVisibility.setImageResource(R.drawable.ic_visible)
            binding.ivVisibility2.setImageResource(R.drawable.ic_visible)
        }else {
            binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.etPasswordAgain.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.etPassword.setSelection(binding.etPassword.text.length)
            binding.etPasswordAgain.setSelection(binding.etPasswordAgain.text.length)
            binding.ivVisibility.setImageResource(R.drawable.ic_invisibility)
            binding.ivVisibility2.setImageResource(R.drawable.ic_invisibility)
        }
    }




}