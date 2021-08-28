package com.shaadi.assignment.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class ImageHelper {

    companion object {
        fun loadImage(view: ImageView, imageUrl: String? = "") {
            Glide.with(view.context)
                .load(imageUrl ?: "")
                .thumbnail(0.1f)
                .apply(
                    RequestOptions()
                        .dontAnimate()
                )
                .into(view)
        }
    }
}