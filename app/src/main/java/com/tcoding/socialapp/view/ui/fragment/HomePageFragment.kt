package com.tcoding.socialapp.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tcoding.socialapp.R
import com.tcoding.socialapp.databinding.FragmentHomePageBinding


class HomePageFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    lateinit var binding: FragmentHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = Firebase.auth
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        binding.textview.setText(auth.currentUser!!.email)
        binding.btnLogOut.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.action_homePageFragment_to_signInFragment)
        }

        return binding.root

    }


}