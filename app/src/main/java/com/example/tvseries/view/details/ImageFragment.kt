package com.example.tvseries.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tvseries.R

class ImageFragment(private val imageSource: String?) : Fragment() {

    private lateinit var fragmentView: View
    private lateinit var imageFragment: ConstraintLayout
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
        imageFragment = fragmentView.findViewById(R.id.image_fragment)
        image = fragmentView.findViewById(R.id.image)

        imageFragment.setOnClickListener {
            // fake onClickListener to prevent releasing onClick in DetailsFragment
        }
    }

    private fun initData() {
        Glide.with(this)
            .load(imageSource)
            .placeholder(ResourcesCompat.getDrawable(resources, R.drawable.ic_movie, null))
            .into(image)
    }
}