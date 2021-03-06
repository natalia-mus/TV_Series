package com.example.tvseries.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvseries.R
import com.example.tvseries.database.FavoriteShow

class FavoriteShowAdapter(
    private val context: Context,
    private val favoriteShows: List<FavoriteShow>,
    private val onItemLongClickAction: OnItemLongClickAction
) :
    RecyclerView.Adapter<FavoriteShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteShowViewHolder {
        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.favorite_show_item, parent, false)

        return FavoriteShowViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: FavoriteShowViewHolder, position: Int) {
        holder.name.text = favoriteShows[position].name
        Glide.with(context).load(favoriteShows[position].image).into(holder.image)

        holder.item.setOnLongClickListener {
            onItemLongClickAction.onItemLongClicked(favoriteShows[position])
            true
        }
    }

    override fun getItemCount() = favoriteShows.size
}


class FavoriteShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val item = view.findViewById<ConstraintLayout>(R.id.favoriteShow_item)
    val image = view.findViewById<ImageView>(R.id.favoriteShow_image)
    val name = view.findViewById<TextView>(R.id.favoriteShow_name)
}


interface OnItemLongClickAction {
    fun onItemLongClicked(item: FavoriteShow)
}