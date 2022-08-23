package com.tcoding.socialapp.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tcoding.socialapp.R
import com.tcoding.socialapp.databinding.FragmentSharingBinding


class SharingFragment : Fragment() {

    val db = Firebase.firestore
    lateinit var auth: FirebaseAuth
    lateinit var binding: FragmentSharingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSharingBinding.inflate(inflater, container, false)

        auth = Firebase.auth
        binding.btnCancel.setOnClickListener {
            canceled()
        }

        binding.btnShare.setOnClickListener {
            shareSomething()
        }



        return binding.root


    }

    fun shareSomething() {
        val sharingMap = HashMap<String, Any>()

        if(!binding.etSharing.text.toString().equals("")) {
            val date = Timestamp.now()
            val sharing = binding.etSharing.text.toString()
            sharingMap.put("email", auth.currentUser!!.email.toString())
            sharingMap.put("date", date)
            sharingMap.put("sharing", sharing)

            db.collection("Sharings")
                .add(sharingMap)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_sharingFragment_to_homePageFragment)
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_SHORT).show()
                }


        }else {
            Toast.makeText(requireContext(), "To be sure all fields full", Toast.LENGTH_SHORT).show()
        }

    }

    fun canceled() {
        findNavController().navigate(R.id.action_sharingFragment_to_homePageFragment)
    }


}