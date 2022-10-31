package com.dempin.base_core.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.canvas.demin.pos.dialog.LoadingDialog

abstract class BaseActivityWithViewModel<DB : ViewDataBinding, VM : ViewModel>
    (private val viewModelClass: Class<VM>) : AppCompatActivity() {

    protected lateinit var binding: DB
    private var loadingDialog: LoadingDialog? = null

    protected val viewModel by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding(LayoutInflater.from(this))
        setContentView(binding.root)
        loadingDialog = LoadingDialog( this)
    }

    protected abstract fun getViewBinding(inflater: LayoutInflater): DB

    protected fun showLoading() {
        loadingDialog?.show()
    }

    protected fun dismissLoading() {
        loadingDialog?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        loadingDialog?.dismiss()
        loadingDialog = null
    }
}
