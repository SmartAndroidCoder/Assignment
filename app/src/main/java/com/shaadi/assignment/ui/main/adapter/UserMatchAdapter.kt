package com.shaadi.assignment.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.shaadi.assignment.data.model.userMaches.User
import com.shaadi.assignment.databinding.ItemUserMatchBinding

class UserMatchAdapter(private val userMatchAdapterListener: UserMatchAdapterListener) :
    ListAdapter<User, UserMatchViewHolder>(UserItemDiffCallback()) {
    interface UserMatchAdapterListener {
        fun updateMatchStatus(email: String, status: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserMatchViewHolder(
            ItemUserMatchBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: UserMatchViewHolder, position: Int) =
        holder.bind(getItem(position), userMatchAdapterListener)

}

