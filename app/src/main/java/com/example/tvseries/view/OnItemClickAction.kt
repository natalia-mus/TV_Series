package com.example.tvseries.view

import com.example.tvseries.datamodel.SingleShow

interface OnItemClickAction {
    fun onItemClicked(item: SingleShow)
}

interface OnItemLongClickAction {
    fun onItemLongClicked(item: SingleShow)
}