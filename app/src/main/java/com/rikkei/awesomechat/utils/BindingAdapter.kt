package com.rikkei.awesomechat.utils

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rikkei.awesomechat.base.UpdateData

@BindingAdapter("app:data")
fun <T> RecyclerView.setDataRecyclerView(data: List<T>?) {
    if (adapter is UpdateData<*> && data != null) {
        (adapter as UpdateData<T>).updateData(data)
    }
}

@BindingAdapter("app:visibility")
fun View.setVisibility(visible: Boolean) {
    isVisible = visible
}
