package com.example.tvseries.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tvseries.R

class ImageFragment(private val imageSource: String?) : Fragment() {

    private lateinit var fragmentView: View
    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentView = inflater.inflate(R.layout.fragment_image, container, false)
        initView(fragmentView)
        initData()
        return fragmentView
    }

    private fun initView(fragmentView: View) {
        image = fragmentView.findViewById(R.id.image)
    }

    private fun initData() {
        Glide.with(this)
            .load(imageSource)
            .placeholder(ResourcesCompat.getDrawable(resources, R.drawable.ic_image_not_found, null))
            .into(image)
    }
}