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
        binding.textView.text = getText(item)
    }

    override fun onViewSetModel(binding: ItemBasicSpinnerBinding, item: T, position: Int) {
       binding.textView.text = getText(item)
    }

    protected abstract fun getText(item: T):String

}