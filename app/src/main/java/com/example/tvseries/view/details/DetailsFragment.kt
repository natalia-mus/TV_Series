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
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.tvseries.R
import com.example.tvseries.contracts.DetailsFragmentContract
import com.example.tvseries.datamodel.TVShow
import com.example.tvseries.objects.Constants
import com.example.tvseries.presenter.DetailsFragmentPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsFragment(
    private val showId: Int?,
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
    private lateinit var description: TextView

    private lateinit var saveButton: ImageButton
    private lateinit var fragmentView: View

    private var isInFavorites = MutableLiveData<Boolean>().apply { this.value = false }
    private var show: TVShow? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentView = inflater.inflate(R.layout.fragment_details, container, false)
        presenter.setViewToPresenter(this)
        showId?.let { presenter.initView(it) }

        isInFavorites.observe(this) { setSaveButtonIconColor(it) }

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
        description = fragmentView.findViewById(R.id.details_description)
        saveButton = fragmentView.findViewById(R.id.details_saveButton)

        image.setOnClickListener() {
            onImageClickAction.onImageClicked(show?.image)
        }

        saveButton.setOnClickListener() {
            handleOnLikeButtonClick()
        }
    }

    override fun updateView(show: TVShow?) {
        if (show != null) {
            initData(show)

        } else {
            // todo
        }
    }

    private fun initData(show: TVShow) {
        this.show = show
        Glide.with(this).load(show.image).into(image)

        name.text = show.name
        network.text = show.network
        country.text = show.country
        startDate.text = show.startDate
        status.text = show.status

        if (show.endDate.isNullOrEmpty()) {
            endDate.text = Constants.NULL
        } else {
            endDate.text = show.endDate
        }

        GlobalScope.launch {
            isInFavorites()
        }
    }

    private fun handleOnLikeButtonClick() {
        if (isInFavorites.value == true) {
            show?.let {
                presenter.deleteShow(it)
                isInFavorites.value = false
            }

        } else {
            show?.let {
                presenter.saveShow(it)
                Toast.makeText(activity, resources.getString(R.string.saved_to_favorites), Toast.LENGTH_SHORT).show()
                isInFavorites.value = true
            }
        }
    }

    private fun setSaveButtonIconColor(isInFavorites: Boolean) {
        var color = resources.getColor(R.color.white, null)

        if (isInFavorites) {
            color = resources.getColor(R.color.pink, null)
        }

        saveButton.drawable.setTint(color)
    }

    private suspend fun isInFavorites() {
        if (show != null) {
            GlobalScope.launch(Dispatchers.IO) {
                val result = presenter.isShowInFavorites(show!!)

                withContext(Dispatchers.Main) {
                    isInFavorites.value = result
                }
            }
        }
    }

}


interface OnImageClickAction {
    fun onImageClicked(image: String?)
}