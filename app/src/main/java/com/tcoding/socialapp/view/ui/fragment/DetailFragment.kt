package com.tcoding.socialapp.view.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tcoding.socialapp.R
import com.tcoding.socialapp.databinding.FragmentDetailBinding
import com.tcoding.socialapp.model.Comment
import com.tcoding.socialapp.model.Share
import com.tcoding.socialapp.view.ui.adapter.CommentAdapter


class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    lateinit var auth: FirebaseAuth

    val db = Firebase.firestore

    lateinit var commentAdapter: CommentAdapter
    var commentList = ArrayList<Comment>()

    val args: DetailFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        auth = Firebase.auth

        addNewComment()
        initView()
        initRecyclerView()

        binding.ibBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homePageFragment)
        }

        return binding.root
    }
    fun addNewComment() {

        binding.btnAddComment.setOnClickListener {
            val commentMap = HashMap<String, Any>()

            if(!binding.etAddComment.equals("")) {
                val date = Timestamp.now()
                val comment = binding.etAddComment.text.toString()
                commentMap.put("email", auth.currentUser!!.email.toString())
                commentMap.put("comment", comment)
                commentMap.put("date", date)

                db.collection("Sharings").document(args.sharing.id).collection("Comments")
                    .add(commentMap)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful) {
                            // İki defa görünme sorunu halledilmeli !!
                            initView()
                        }
                    }.addOnFailureListener { exception ->
                        Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    fun initRecyclerView() {
        binding.rvComments.layoutManager = LinearLayoutManager(requireContext())
        commentAdapter = CommentAdapter(commentList)
        binding.rvComments.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        binding.rvComments.adapter = commentAdapter
    }

    fun initView() {
        commentList.clear()
        binding.tvEmail.text = args.sharing.email
        binding.tvSharing.text = args.sharing.sharing
         db.collection("Sharings").document(args.sharing.id).collection("Comments")
             .orderBy("date", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if(error == null) {
                    if(value != null) {
                        if(!value.isEmpty) {
                            val documents = value.documents

                            for(document in documents) {
                                val email = document.get("email") as String
                                val comment = document.get("comment") as String

                                commentList.add(Comment(email, comment))
                            }
                            commentAdapter.notifyDataSetChanged()

                        }
                    }
                }else {
                    Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            }
    }


}