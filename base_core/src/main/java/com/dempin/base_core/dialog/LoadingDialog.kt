package com.dempin.base_core.dialog

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.dempin.base_core.R
import com.dempin.base_core.base.BaseDialog
import com.dempin.base_core.databinding.DialogLoadingBinding

class LoadingDialog(private val activity: Activity) : BaseDialog<DialogLoadingBinding>(activity) {

    private var message: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
        binding?.imageView?.let {
            Glide.with(activity).load(R.drawable.loading).into(it)
        }
        if(message.isNotEmpty()){
            binding?.textView?.visibility = View.VISIBLE
            binding?.textView?.text = message
        }
    }

    override fun getViewDataBinding(layoutInflater: LayoutInflater): DialogLoadingBinding {
        return DialogLoadingBinding.inflate(layoutInflater)
    }

    fun show(message: String) {
        this.message = message
        show()
    }

    override fun dismiss() {
        message = ""
        super.dismiss()
    }

}