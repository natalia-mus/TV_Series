package com.example.tvseries.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvseries.R
import com.example.tvseries.datamodel.TVSeries
import com.example.tvseries.view.OnItemClickAction

class TVShowsAdapter(
    private val context: Context,
    tvSeries: TVSeries,
    private val onItemClickAction: OnItemClickAction
) :
    RecyclerView.Adapter<SingleShowAdapterViewHolder>() {

    private val seriesList = tvSeries.tvShows

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleShowAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(context).inflate(R.layout.single_show_item, parent, false)
        return SingleShowAdapterViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: SingleShowAdapterViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(seriesList[position].image)
            .placeholder(ResourcesCompat.getDrawable(context.resources, R.drawable.ic_image_not_found, null))
            .into(holder.showImage)

        holder.showItem.setOnClickListener() {
            onItemClickAction.onItemClicked(seriesList[position].id)
        }
    }

    override fun getItemCount() = seriesList.size
}


class SingleShowAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val showItem: ConstraintLayout = itemView.findViewById(R.id.showItem)
    val showImage: ImageView = itemView.findViewById(R.id.showItem_image)
}
