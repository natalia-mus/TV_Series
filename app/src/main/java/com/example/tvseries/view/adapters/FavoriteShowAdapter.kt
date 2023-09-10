package com.example.tvseries.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvseries.R
import com.example.tvseries.datamodel.TVShowForDatabase
import com.example.tvseries.view.OnItemClickAction
import com.example.tvseries.view.OnItemLongClickAction

class FavoriteShowAdapter(
    private val context: Context,
    private var favoriteShows: List<TVShowForDatabase>,
    private val onItemClickAction: OnItemClickAction,
    private val onItemLongClickAction: OnItemLongClickAction
) :
    RecyclerView.Adapter<FavoriteShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteShowViewHolder {
        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.favorite_show_item, parent, false)

        return FavoriteShowViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: FavoriteShowViewHolder, position: Int) {
        val item = favoriteShows[position]

        holder.name.text = item.name
        Glide.with(context)
            .load(item.image)
            .placeholder(ResourcesCompat.getDrawable(context.resources, R.drawable.ic_movie, null))
            .into(holder.image)

        holder.item.setOnClickListener {
            onItemClickAction.onItemClicked(item.id)
        }

        holder.item.setOnLongClickListener {
            onItemLongClickAction.onItemLongClicked(item)
            true
        }
    }

    override fun getItemCount() = favoriteShows.size

    fun dataSetChanged(newDataSet: List<TVShowForDatabase>) {
        favoriteShows = newDataSet
        notifyDataSetChanged()
    }
}


class FavoriteShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val item: ConstraintLayout = view.findViewById(R.id.favoriteShow_item)
    val image: ImageView = view.findViewById(R.id.favoriteShow_image)
    val name: TextView = view.findViewById(R.id.favoriteShow_name)
}
