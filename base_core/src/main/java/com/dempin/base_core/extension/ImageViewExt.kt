package com.dempin.base_core.extension

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(path:String){
    Glide.with(this).load(path).into(this)
}

fun ImageView.loadImage(resourceId: Int) {
    Glide.with(this)
        .load(resourceId)
        .into(this)
}

fun AppCompatImageView.loadImage(bitmap: Bitmap){
    Glide.with(this).load(bitmap).into(this)
}