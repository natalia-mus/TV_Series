package com.example.tvseries.view.favoriteshows

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvseries.R
import com.example.tvseries.contracts.FavoriteShowsActivityContract
import com.example.tvseries.datamodel.TVShowForDatabase
import com.example.tvseries.objects.Constants
import com.example.tvseries.presenter.FavoriteShowsActivityPresenter
import com.example.tvseries.view.OnItemClickAction
import com.example.tvseries.view.OnItemLongClickAction
import com.example.tvseries.view.adapters.FavoriteShowAdapter
import com.example.tvseries.view.details.DetailsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteShowsActivity : AppCompatActivity(),
    FavoriteShowsActivityContract.FavoriteShowsActivityView,
    OnItemClickAction,
    OnItemLongClickAction {

    private val context: Context = this
    private val presenter = FavoriteShowsActivityPresenter()

    private lateinit var favoriteShowsRecyclerView: RecyclerView
    private lateinit var noFavoriteShowsInfo: TextView
    private lateinit var favoriteShowsListSection: LinearLayout
    private lateinit var progressBar: ProgressBar

    private var adapter: FavoriteShowAdapter? = null
    private var lockLongClick = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_shows)

        presenter.setViewToPresenter(this)
        presenter.initView()
    }

    override fun initView() {
        noFavoriteShowsInfo = findViewById(R.id.favoriteShows_noFavoriteShowsInfo)
        favoriteShowsListSection = findViewById(R.id.favoriteShows_favoriteShowsListSection)
        favoriteShowsRecyclerView = findViewById(R.id.favoriteShows_favoriteShowsList)
        progressBar = findViewById(R.id.favoriteShows_progressBar)
        favoriteShowsRecyclerView.layoutManager = GridLayoutManager(this, 3)
    }

    override fun updateView() {
        initData()
    }

    override fun onItemClicked(itemId: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(Constants.ITEM_ID, itemId)
        startActivity(intent)
    }

    override fun onItemLongClicked(item: TVShowForDatabase) {
        if (!lockLongClick) {
            progressBar.visibility = View.VISIBLE
            lockLongClick = true
            presenter.deleteShow(item)
        }
    }

    override fun initData() {
        GlobalScope.launch(Dispatchers.IO) {
            val data = presenter.returnData()

            withContext(Dispatchers.Main) {
                refreshView(data)
            }
        }
    }

    private fun refreshView(data: List<TVShowForDatabase>) {
        lockLongClick = false
        progressBar.visibility = View.GONE

        if (data.isEmpty()) {
            favoriteShowsListSection.visibility = View.GONE
            noFavoriteShowsInfo.visibility = View.VISIBLE
        } else {
            if (adapter == null) {
                favoriteShowsRecyclerView.adapter = FavoriteShowAdapter(context, data, this@FavoriteShowsActivity, this@FavoriteShowsActivity)
                adapter = favoriteShowsRecyclerView.adapter as FavoriteShowAdapter

            } else {
                adapter!!.dataSetChanged(data)
            }
        }
    }

}