package com.david0926.foodinfo.ui.main

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.david0926.foodinfo.data.model.Food
import com.david0926.foodinfo.util.Status

@BindingAdapter("bindFoods")
fun bindFoods(recycler: RecyclerView, foods: ObservableArrayList<Food>?) {
    val adapter = recycler.adapter as MainRecyclerAdapter? ?: return
    if (foods != null) adapter.submitList(foods.toMutableList())
}

@BindingAdapter("bindSwipeRefreshing")
fun bindSwipeRefreshing(swipeRefresh: SwipeRefreshLayout, status: Status?) {
    if (status != null) swipeRefresh.isRefreshing = (status == Status.LOADING)
}

@BindingAdapter("bindSwipeOnRefresh")
fun bindSwipeOnRefresh(swipeRefresh: SwipeRefreshLayout, job: () -> Unit) {
    swipeRefresh.setOnRefreshListener(job)
}

@BindingAdapter("bindDoAfterTextChanged")
fun bindDoAfterTextChanged(editText: EditText, job: () -> Unit) {
    editText.doAfterTextChanged { job() }
}

@BindingAdapter(value = ["bindRecyclerOnScrollEnd", "bindRecyclerTotalItem"], requireAll = true)
fun bindRecyclerOnScrollEnd(recycler: RecyclerView, job: () -> Unit, total: Int) {
    val layoutManager = recycler.layoutManager as GridLayoutManager? ?: return

    recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= total
            ) job()
        }
    })
}