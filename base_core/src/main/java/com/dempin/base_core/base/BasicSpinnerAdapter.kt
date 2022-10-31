package com.dempin.base_core.base

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dempin.base_core.databinding.ItemBasicSpinnerBinding

abstract class BasicSpinnerAdapter<T> :BaseSpinnerAdapter<T,ItemBasicSpinnerBinding,ItemBasicSpinnerBinding>() {
    override fun getViewDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): ItemBasicSpinnerBinding {
        return ItemBasicSpinnerBinding.inflate(layoutInflater,parent,false)
    }

    override fun getViewDataBindingDropDown(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): ItemBasicSpinnerBinding {
        return ItemBasicSpinnerBinding.inflate(layoutInflater,parent,false)
    }

    override fun onDropDownViewSetModel(binding: ItemBasicSpinnerBinding, item: T, position: Int) {
        onSetModel(binding, item, position)
    }

    override fun onViewSetModel(binding: ItemBasicSpinnerBinding, item: T, position: Int) {
        onSetModel(binding, item, position)
    }

    protected abstract fun onSetModel(binding: ItemBasicSpinnerBinding,item: T,position: Int)

}