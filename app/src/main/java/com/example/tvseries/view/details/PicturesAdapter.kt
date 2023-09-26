package com.example.tvseries.view.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvseries.R
import com.example.tvseries.datamodel.Pictures

class PicturesAdapter(private val context: Context, private val pictures: Pictures, private val onImageClickAction: OnImageClickAction) : RecyclerView.Adapter<PicturesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        val layoutInflater = LayoutInflater.from(context).inflate(R.layout.picture_item, parent, false)
        return PicturesViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        Glide.with(holder.picture)
            .load(pictures.pictures[position])
            .centerCrop()
            .override(100, 100)
            .into(holder.picture)

        holder.picture.setOnClickListener {
            onImageClickAction.onImageClicked(pictures.pictures[position], false, pictures.pictures)
        }
    }

    override fun getItemCount() = pictures.pictures.size

}

class PicturesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val picture: ImageView = view.findViewById(R.id.picture_item)
}