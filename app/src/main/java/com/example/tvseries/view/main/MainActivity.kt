package com.example.tvseries.view.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvseries.BaseApplication
import com.example.tvseries.R
import com.example.tvseries.contracts.MainActivityContract
import com.example.tvseries.datamodel.SingleShow
import com.example.tvseries.objects.Constants
import com.example.tvseries.presenter.MainActivityPresenter
import com.example.tvseries.view.OnItemClickAction
import com.example.tvseries.view.adapters.SingleShowAdapter
import com.example.tvseries.view.details.DetailsActivity
import com.example.tvseries.view.favoriteshows.FavoriteShowsActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.MainActivityView, OnItemClickAction {

    @Inject
    lateinit var presenter: MainActivityPresenter

    private lateinit var progressBar: ProgressBar
    private lateinit var errorInfo: TextView
    private lateinit var seriesListRecyclerView: RecyclerView
    private lateinit var hintSection: ConstraintLayout
    private lateinit var searchField: EditText
    private lateinit var searchButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).getBaseApplicationComponent().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()

        presenter.setViewToPresenter(this)
        presenter.fetchAllSeries()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.goToFavorites -> {
                val intent = Intent(this, FavoriteShowsActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initView() {
        progressBar = findViewById(R.id.main_progressBar)
        errorInfo = findViewById(R.id.main_errorInfo)
        seriesListRecyclerView = findViewById(R.id.main_seriesList)
        hintSection = findViewById(R.id.main_hintSection)
        seriesListRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun updateView() {
        val status = presenter.returnStatus()
        progressBar.visibility = View.INVISIBLE

        if (status) {
            val data = presenter.returnData()
            seriesListRecyclerView.adapter = SingleShowAdapter(this, data, this)
            hintSection.visibility = View.VISIBLE
        } else {
            errorInfo.visibility = View.VISIBLE
            Toast.makeText(this, resources.getString(R.string.data_can_not_be_loaded), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClicked(item: SingleShow) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(Constants.ITEM, item)
        startActivity(intent)
    }

    private fun hideKeyboard() {
        val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = (this as Activity).findViewById<View>(android.R.id.content).rootView
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun searchByPhrase() {
        hideKeyboard()
        val phrase = searchField.text.toString()
        presenter.fetchMatchingSeries(phrase)
    }

    private fun setToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun setView() {
        setToolbar()

        searchButton = findViewById(R.id.toolbar_search_button)
        searchField = findViewById(R.id.toolbar_search_field)

        searchButton.setOnClickListener {
            searchByPhrase()
        }
    }

}