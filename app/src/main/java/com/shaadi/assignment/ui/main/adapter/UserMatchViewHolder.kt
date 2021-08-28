package com.shaadi.assignment.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.shaadi.assignment.R
import com.shaadi.assignment.data.model.userMaches.User
import com.shaadi.assignment.databinding.ItemUserMatchBinding
import com.shaadi.assignment.utils.hide
import com.shaadi.assignment.utils.loadImage
import com.shaadi.assignment.utils.show

class UserMatchViewHolder(private val binding: ItemUserMatchBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object{
         const val ACCEPTED =1
         const val DECLINED =2
    }
    fun bind(user: User, userMatchAdapterListener: UserMatchAdapter.UserMatchAdapterListener) {

        binding.tvNameAge.text =
            "${user.name.title} ${user.name.first} ${user.name.last} (${user.dob.age})"

        binding.tvLocation.text =
            "${user.location.city}, ${user.location.state}, ${user.location.country}"

        user.picture?.large?.let { binding.ivProfileImage.loadImage(it) }
        binding.btnAccept.setOnClickListener {
            userMatchAdapterListener.updateMatchStatus(user.email, ACCEPTED)
        }
        binding.btnDecline.setOnClickListener {
            userMatchAdapterListener.updateMatchStatus(user.email, DECLINED)
        }
        setStatus(user)
    }

    private fun setStatus(user: User) {
        when (user.matchStatus) {
            ACCEPTED -> {
                binding.tvMatchStatus.text =
                    binding.root.context.getString(R.string.member_accepted)
                binding.tvMatchStatus.show()
                binding.gpActionBtn.hide()
            }
            DECLINED -> {
                binding.tvMatchStatus.text =
                    binding.root.context.getString(R.string.member_declined)
                binding.tvMatchStatus.show()
                binding.gpActionBtn.hide()
            }
            else -> {
                binding.tvMatchStatus.hide()
                binding.gpActionBtn.show()
            }
        }
    }

}