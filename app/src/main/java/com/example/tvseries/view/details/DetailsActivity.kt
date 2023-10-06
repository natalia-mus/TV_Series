package com.example.tvseries.view.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tvseries.R
import com.example.tvseries.objects.Constants

class DetailsActivity : AppCompatActivity(), OnImageClickAction {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val showId: Int = intent.getIntExtra(Constants.ITEM_ID, -1)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.details_fragment, DetailsFragment(showId, this@DetailsActivity))
            commit()
        }

    }

    override fun onImageClicked(image: String?, isPoster: Boolean, images: ArrayList<String>?) {
        supportFragmentManager.beginTransaction()
            .add(R.id.details_fragment, ImageFragment(image, isPoster, images))
            .addToBackStack(null)
            .commit()
    }
}