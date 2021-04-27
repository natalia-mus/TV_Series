package com.example.tvseries.view.favoriteshows

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvseries.R
import com.example.tvseries.contracts.FavoriteShowsActivityContract
import com.example.tvseries.database.FavoriteShow
import com.example.tvseries.presenter.FavoriteShowsActivityPresenter
import com.example.tvseries.view.adapters.FavoriteShowAdapter
import com.example.tvseries.view.adapters.OnItemLongClickAction
import kotlinx.coroutines.*

class FavoriteShowsActivity : AppCompatActivity(),
    FavoriteShowsActivityContract.FavoriteShowsActivityView,
    OnItemLongClickAction {

    private val context: Context = this
    private val presenter = FavoriteShowsActivityPresenter()
    private lateinit var favoriteShowsRecyclerView: RecyclerView
    private lateinit var noFavoriteShowsInfo: TextView
    private lateinit var favoriteShowsListSection: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_shows)

        presenter.setViewToPresenter(this)
        presenter.initView()
    }

    override fun initView() {
        noFavoriteShowsInfo = findViewById(R.id.noFavoriteShowsInfo)
        favoriteShowsListSection = findViewById(R.id.favoriteShowsList_section)
        favoriteShowsRecyclerView = findViewById(R.id.recyclerView_favoriteShowsList)
        favoriteShowsRecyclerView.layoutManager = GridLayoutManager(this, 3)
    }

    override fun initData() {
        GlobalScope.async {
            val data = presenter.returnData()

            if (data.isEmpty()) {
                favoriteShowsListSection.visibility = View.INVISIBLE
                noFavoriteShowsInfo.visibility = View.VISIBLE
            } else {
                favoriteShowsRecyclerView.adapter =
                    FavoriteShowAdapter(context, data, this@FavoriteShowsActivity)
            }
        }
    }

    override fun updateView() {
        runOnUiThread {
            onResume()
            initView()
            initData()
        }
    }

    override fun onItemLongClicked(item: FavoriteShow) {
        presenter.delete(item)
    }
}