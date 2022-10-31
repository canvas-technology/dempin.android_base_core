package com.dempin.base_core.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.ViewDataBinding

abstract class BaseSpinnerAdapter<T, VDB : ViewDataBinding, DVDB : ViewDataBinding>(
    private var items: MutableList<T> = arrayListOf()
) : BaseAdapter() {

    @Suppress("UNCHECKED_CAST")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        val binding: VDB
        if (view == null) {
            binding = getViewDataBinding(getInflater(parent),parent)
            view = binding.root
            view.tag = binding
        } else {
            binding = view.tag as VDB
        }
        onViewSetModel(binding, getItem(position),position)
        return view
    }


    @Suppress("UNCHECKED_CAST")
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        val binding: DVDB
        if (view == null) {
            binding = getViewDataBindingDropDown(getInflater(parent),parent)
            view = binding.root
            view.tag = binding
        } else {
            binding = view.tag as DVDB
        }
        onDropDownViewSetModel(binding, getItem(position),position)
        return view
    }

    override fun getItem(position: Int): T = items[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getCount(): Int = items.size


    fun setItems(items: List<T>) {
        this.items = items as MutableList<T>
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
    }

    private fun getInflater(parent: ViewGroup): LayoutInflater {
        return LayoutInflater.from(parent.context)
    }


    protected abstract fun getViewDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): VDB

    protected abstract fun getViewDataBindingDropDown(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): DVDB

    protected abstract fun onViewSetModel(binding: VDB, item: T,position: Int)
    protected abstract fun onDropDownViewSetModel(binding: DVDB, item: T,position: Int)

}