package com.dempin.base_core.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dempin.base_core.dialog.LoadingDialog
import com.dempin.base_core.utils.EncryptedSharedPreferencesHelper
import kotlin.system.exitProcess

abstract class BaseFragmentWithViewModel<DB : ViewDataBinding, VM : ViewModel>
    (private val viewModelClass: Class<VM>) : Fragment() {

    protected lateinit var binding: DB
    private var loadingDialog: LoadingDialog? = null

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getViewDataBinding(inflater, container)
        activity?.let {
            loadingDialog = LoadingDialog(it)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loadingDialog?.dismiss()
        loadingDialog = null
    }

    protected fun showToast(message:String){
        Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
    }

    protected abstract fun getViewDataBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup?
    ): DB

    fun showLoading() {
        activity?.runOnUiThread {
            loadingDialog?.show()
        }
    }

    fun dismissLoading() {
        activity?.runOnUiThread {
            loadingDialog?.dismiss()
        }
    }

    protected fun logout(
        activityClass: Class<*>,
        sharedPreferencesHelper: EncryptedSharedPreferencesHelper
    ) {
        sharedPreferencesHelper.clear()
        val intent = Intent(activity, activityClass).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        activity?.finishAffinity()
        exitProcess(0)
    }

}