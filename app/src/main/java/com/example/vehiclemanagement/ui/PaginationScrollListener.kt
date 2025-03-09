package com.example.vehiclemanagement.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {
    private val threshold = 4

    abstract val isLastPage: Boolean
    abstract fun isLoading(): Boolean

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

            if (isLastPage) {
                return
            }

            if (visibleItemCount + firstVisibleItemPosition + threshold >= totalItemCount) {
                if (!isLoading()) {
                    loadMore()
                }
            }
        }
    }

    abstract fun loadMore()
}