package com.example.tvseries.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvseries.BaseApplication
import com.example.tvseries.R
import com.example.tvseries.contracts.MainActivityContract
import com.example.tvseries.datamodel.SingleShow
import com.example.tvseries.presenter.MainActivityPresenter
import com.example.tvseries.view.adapters.OnItemClickAction
import com.example.tvseries.view.adapters.SingleShowAdapter
import com.example.tvseries.view.details.DetailsActivity
import com.example.tvseries.view.favoriteshows.FavoriteShowsActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.MainActivityView, OnItemClickAction {

    private lateinit var progressBar: ProgressBar
    private lateinit var errorInfo: TextView
    private lateinit var seriesListRecyclerView: RecyclerView
    private lateinit var hintSection: ConstraintLayout

    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).getBaseApplicationComponent().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.setViewToPresenter(this)
        presenter.fetchData()
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
        progressBar = findViewById(R.id.progressBar)
        errorInfo = findViewById(R.id.errorInfo)
        seriesListRecyclerView = findViewById(R.id.recyclerView_seriesList)
        hintSection = findViewById(R.id.hint_section)
        seriesListRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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
            Toast.makeText(this, "Data can not be loaded.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClicked(item: SingleShow) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("item", item)
        startActivity(intent)
    }

}