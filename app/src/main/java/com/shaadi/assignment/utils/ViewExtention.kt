package com.shaadi.assignment.utils

import android.view.View
import android.widget.ImageView


fun ImageView.loadImage(url: String) {
    ImageHelper.loadImage(this, imageUrl = url)
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}