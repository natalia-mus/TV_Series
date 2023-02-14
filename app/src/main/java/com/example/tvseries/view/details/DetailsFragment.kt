package com.example.tvseries.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tvseries.R
import com.example.tvseries.contracts.DetailsFragmentContract
import com.example.tvseries.database.FavoriteShow
import com.example.tvseries.datamodel.SingleShow
import com.example.tvseries.objects.Constants
import com.example.tvseries.presenter.DetailsFragmentPresenter

class DetailsFragment(
    private val item: SingleShow?,
    private val onImageClickAction: OnImageClickAction
) : Fragment(), DetailsFragmentContract.DetailsFragmentView {

    val presenter = DetailsFragmentPresenter()

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var network: TextView
    private lateinit var country: TextView
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var status: TextView

    lateinit var saveButton: ImageButton
    lateinit var fragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentView = inflater.inflate(R.layout.fragment_details, container, false)
        presenter.setViewToPresenter(this)
        presenter.initView()

        return fragmentView
    }

    override fun initView() {
        image = fragmentView.findViewById(R.id.details_image)
        name = fragmentView.findViewById(R.id.details_name)
        network = fragmentView.findViewById(R.id.details_network)
        country = fragmentView.findViewById(R.id.details_country)
        startDate = fragmentView.findViewById(R.id.details_startDate)
        endDate = fragmentView.findViewById(R.id.details_endDate)
        status = fragmentView.findViewById(R.id.details_status)
        saveButton = fragmentView.findViewById(R.id.details_saveButton)

        image.setOnClickListener() {
            onImageClickAction.onImageClicked(item?.image)
        }

        saveButton.setOnClickListener() {
            saveShow()
        }
    }

    override fun initData() {
        Glide.with(this).load(item?.image).into(image)

        name.text = item?.name
        network.text = item?.network
        country.text = item?.country
        startDate.text = item?.startDate
        status.text = item?.status

        if (item?.endDate.isNullOrEmpty()) {
            endDate.text = Constants.NULL
        } else {
            endDate.text = item?.endDate
        }
    }

    private fun saveShow() {
        val id = item?.id.toString().toInt()
        val name = item?.name.toString()
        val image = item?.image.toString()

        val favoriteShow = FavoriteShow(id, name, image)
        presenter.saveShow(favoriteShow)
        Toast.makeText(activity, resources.getString(R.string.saved_to_favorites), Toast.LENGTH_SHORT).show()
    }

}


interface OnImageClickAction {
    fun onImageClicked(image: String?)
}