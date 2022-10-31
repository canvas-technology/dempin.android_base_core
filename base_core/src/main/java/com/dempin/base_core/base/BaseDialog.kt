package com.dempin.base_core.base

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding

abstract class BaseDialog<VDB : ViewDataBinding>(activity: Activity) : Dialog(activity) {

    protected lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(context)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = getViewDataBinding(inflater)
        setContentView(binding.root)
    }

    protected abstract fun getViewDataBinding(layoutInflater: LayoutInflater): VDB

}