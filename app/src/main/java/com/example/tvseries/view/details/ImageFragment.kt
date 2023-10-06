package com.example.tvseries.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tvseries.R

class ImageFragment(private var currentImage: String?, private val isPoster: Boolean, private val images: ArrayList<String>?) : Fragment() {

    private lateinit var fragmentView: View
    private lateinit var imageFragment: ConstraintLayout
    private lateinit var image: ImageView
    private lateinit var left: ImageView
    private lateinit var right: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentView = inflater.inflate(R.layout.fragment_image, container, false)
        initView(fragmentView)
        loadImage()
        return fragmentView
    }

    private fun initView(fragmentView: View) {
        imageFragment = fragmentView.findViewById(R.id.image_fragment)
        image = fragmentView.findViewById(R.id.image)
        left = fragmentView.findViewById(R.id.image_left)
        right = fragmentView.findViewById(R.id.image_right)

        imageFragment.setOnClickListener {
            // fake onClickListener to prevent releasing onClick in DetailsFragment
        }

        if (!isPoster) {
            left.visibility = View.VISIBLE
            right.visibility = View.VISIBLE

            left.setOnClickListener {
                previousImage()
            }

            right.setOnClickListener {
                nextImage()
            }
        }
    }

    private fun nextImage() {
        if (images != null) {
            val currentImageIndex = images.indexOf(currentImage)

            val nextImage = if (currentImageIndex + 1 == images.size) {
                images[0]
            } else {
                images[currentImageIndex + 1]
            }

            currentImage = nextImage
            loadImage()
        }
    }

    private fun previousImage() {
        if (images != null) {
            val currentImageIndex = images.indexOf(currentImage)

            val previousImage = if (currentImageIndex == 0) {
                images[images.lastIndex]
            } else {
                images[currentImageIndex - 1]
            }

            currentImage = previousImage
            loadImage()
        }
    }

    private fun loadImage() {
        Glide.with(this)
            .load(currentImage)
            .into(image)
    }
}