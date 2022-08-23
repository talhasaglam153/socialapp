package com.tcoding.socialapp.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tcoding.socialapp.R
import com.tcoding.socialapp.model.Comment
import com.tcoding.socialapp.model.Share

class CommentAdapter(var commentList: ArrayList<Comment>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvEmail: TextView
        var tvSharing: TextView

        init {
            tvEmail = itemView.findViewById(R.id.tvEmail)
            tvSharing = itemView.findViewById(R.id.tvSharing)
        }

        fun bindData(comment: Comment) {
            tvEmail.setText(comment.email)
            tvSharing.setText(comment.comment)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sharing_row, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindData(commentList[position])
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
}