package com.shaadi.assignment.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.shaadi.assignment.data.model.userMaches.User

class UserItemDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem

}