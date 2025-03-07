package com.example.vehiclemanagement.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.vehiclemanagement.R

@BindingAdapter("urlSrc")
fun setUrlSrc(view: ImageView, url: String?) {
    Glide.with(view.rootView)
        .load(url)
        .placeholder(R.drawable.vehicle_placeholder)
        .fitCenter()
        .into(view)
}

