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
import com.tcoding.socialapp.R
import com.tcoding.socialapp.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.ivVisibility.setOnClickListener {
           icVisibleOnClickListener()
        }
        binding.ivVisibility2.setOnClickListener {
            icInvisibleOnClickListener()
        }

        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        return binding.root

    }
    fun icVisibleOnClickListener() {
        if(binding.etPassword.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.etPasswordAgain.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.etPassword.setSelection(binding.etPassword.text.length)
            binding.etPasswordAgain.setSelection(binding.etPassword.text.length)
            binding.ivVisibility.setImageResource(R.drawable.ic_visible)
            binding.ivVisibility2.setImageResource(R.drawable.ic_visible)
        }else {
            binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.etPasswordAgain.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.ivVisibility.setImageResource(R.drawable.ic_invisibility)
            binding.etPassword.setSelection(binding.etPassword.text.length)
            binding.etPasswordAgain.setSelection(binding.etPassword.text.length)
            binding.ivVisibility2.setImageResource(R.drawable.ic_invisibility)
        }
    }
    fun icInvisibleOnClickListener() {
        if(binding.etPassword.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.etPasswordAgain.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.etPassword.setSelection(binding.etPassword.text.length)
            binding.etPasswordAgain.setSelection(binding.etPassword.text.length)
            binding.ivVisibility.setImageResource(R.drawable.ic_visible)
            binding.ivVisibility2.setImageResource(R.drawable.ic_visible)
        }else {
            binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.etPasswordAgain.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.etPassword.setSelection(binding.etPassword.text.length)
            binding.etPasswordAgain.setSelection(binding.etPassword.text.length)
            binding.ivVisibility.setImageResource(R.drawable.ic_invisibility)
            binding.ivVisibility2.setImageResource(R.drawable.ic_invisibility)
        }
    }




}