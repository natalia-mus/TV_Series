package com.example.tvseries.view.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tvseries.R
import com.example.tvseries.datamodel.SingleShow

class DetailsActivity : AppCompatActivity(), OnImageClickAction {

    private lateinit var onImageClicked: OnImageClickAction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val item: SingleShow? = intent.getParcelableExtra("item")
        onImageClicked = this

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_details_fragment, DetailsFragment(item, onImageClicked))
            commit()
        }

    }

    override fun onImageClicked(image: String?) {
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_details_fragment, ImageFragment(image))
            .addToBackStack(null)
            .commit()
    }
}