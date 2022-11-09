package com.dempin.base_core.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T, VDB : ViewDataBinding>
    (private var items: MutableList<T> = arrayListOf()) :
    RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = getViewBinding(getInflater(parent), parent)
        return ViewHolder(binding)
    }

    private fun getInflater(parent: ViewGroup): LayoutInflater {
        return LayoutInflater.from(parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        setData(
            item,
            position,
            holder.itemBinding as VDB
        )
    }

    override fun getItemCount(): Int = items.size


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items:List<T>){
        this.items = items as MutableList<T>
        notifyDataSetChanged()
    }

    fun getItems():List<T> = items
    fun clearItems() = items.clear()

    class ViewHolder(
        val itemBinding : ViewDataBinding
    ) : RecyclerView.ViewHolder(itemBinding.root)

    protected abstract fun setData(item: T, position: Int, binding: VDB)

    protected abstract fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): VDB
}