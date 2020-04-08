package me.weicools.multitype

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author weicools
 * @date 2020.02.09
 * @implNote This is a compatible version of [ItemViewDelegate].
 * @see ItemViewDelegate
 */
abstract class ItemViewBinder<T, VH : RecyclerView.ViewHolder> : ItemViewDelegate<T, VH>() {
  final override fun onCreateViewHolder(context: Context, parent: ViewGroup): VH {
    return onCreateViewHolder(LayoutInflater.from(context), parent)
  }

  abstract fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH
}