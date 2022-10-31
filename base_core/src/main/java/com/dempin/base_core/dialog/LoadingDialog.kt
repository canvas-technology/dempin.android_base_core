package com.dempin.base_core.dialog

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.dempin.base_core.R
import com.dempin.base_core.base.BaseDialog
import com.dempin.base_core.databinding.DialogLoadingBinding

class LoadingDialog(private val activity:Activity) : BaseDialog<DialogLoadingBinding>(activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
        Glide.with(activity).load(R.drawable.loading).into(binding.imageView)
    }

    override fun getViewDataBinding(layoutInflater: LayoutInflater): DialogLoadingBinding {
        return DialogLoadingBinding.inflate(layoutInflater)
    }

    fun show(message:String){
        binding.textView.visibility = View.VISIBLE
        binding.textView.text = message
        show()
    }

    override fun dismiss() {
        binding.textView.visibility = View.GONE
        binding.textView.text = ""
        super.dismiss()
    }
}