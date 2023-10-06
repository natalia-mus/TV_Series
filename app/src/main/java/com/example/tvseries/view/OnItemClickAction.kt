package com.example.tvseries.view

import com.example.tvseries.datamodel.TVShowForDatabase

interface OnItemClickAction {
    fun onItemClicked(showId: Int)
}

interface OnItemLongClickAction {
    fun onItemLongClicked(item: TVShowForDatabase)
}