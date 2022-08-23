package com.tcoding.socialapp.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tcoding.socialapp.R
import com.tcoding.socialapp.model.Share
import org.w3c.dom.Text

class SharingAdapter(var sharingList: ArrayList<Share>, var onClick: ((position: Int)->Unit)): RecyclerView.Adapter<SharingAdapter.SharingViewHolder>() {

    class SharingViewHolder(itemView: View, onClick: (position: Int) -> Unit): RecyclerView.ViewHolder(itemView) {
        var tvEmail: TextView
        var tvSharing: TextView

        init {
            tvEmail = itemView.findViewById(R.id.tvEmail)
            tvSharing = itemView.findViewById(R.id.tvSharing)

            itemView.setOnClickListener {
                onClick!!(adapterPosition)
            }
        }

        fun bindData(share: Share) {
            tvEmail.setText(share.email)
            tvSharing.setText(share.sharing)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SharingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sharing_row, parent, false)
        return SharingViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: SharingViewHolder, position: Int) {
        holder.bindData(sharingList[position])
    }

    override fun getItemCount(): Int {
        return sharingList.size
    }
}