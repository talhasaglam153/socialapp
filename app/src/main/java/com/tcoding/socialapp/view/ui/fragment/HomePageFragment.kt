package com.tcoding.socialapp.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import com.tcoding.socialapp.R
import com.tcoding.socialapp.databinding.FragmentHomePageBinding
import com.tcoding.socialapp.model.Share
import com.tcoding.socialapp.view.ui.adapter.SharingAdapter


class HomePageFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    lateinit var sharingAdapter: SharingAdapter
    lateinit var binding: FragmentHomePageBinding
    var sharingList = ArrayList<Share>()
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

        /*binding.textview.setText(auth.currentUser!!.email)
        binding.btnLogOut.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.action_homePageFragment_to_signInFragment)
        }*/
        initView()
        initRecyclerView()

        binding.fabShare.setOnClickListener {
            toSharingScreen()
        }

        return binding.root

    }

    fun initRecyclerView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        sharingAdapter = SharingAdapter(sharingList, ::itemClickListener)
        binding.rv.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        binding.rv.adapter = sharingAdapter
    }

    fun itemClickListener(position: Int) {
        var direction = HomePageFragmentDirections.actionHomePageFragmentToDetailFragment(sharingList[position])
        findNavController().navigate(direction)
    }

    fun initView() {
        sharingList.clear()

        db.collection("Sharings").orderBy("date", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if(error == null) {
                    if(value != null) {
                        if(!value.isEmpty) {
                            val documents = value.documents
                            for(document in documents) {
                                val email = document.get("email") as String
                                val sharing = document.get("sharing") as String
                                val id = document.id
                                sharingList.add(Share(id, email, sharing))

                            }
                            // Adapter notify data set changed
                            sharingAdapter.notifyDataSetChanged()

                        }
                    }
                }else {
                    Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }

    }

    fun toSharingScreen() {
        findNavController().navigate(R.id.action_homePageFragment_to_sharingFragment)
    }


}