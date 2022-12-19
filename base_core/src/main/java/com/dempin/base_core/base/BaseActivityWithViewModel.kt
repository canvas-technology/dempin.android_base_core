package com.dempin.base_core.base

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.PackageInfoCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dempin.base_core.dialog.LoadingDialog
import com.dempin.base_core.utils.EncryptedSharedPreferencesHelper
import kotlin.system.exitProcess

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
        loadingDialog = LoadingDialog(this)
    }

    protected abstract fun getViewBinding(inflater: LayoutInflater): DB

    protected fun showLoading() {
        loadingDialog?.show()
    }

    protected fun showLoading(message: String) {
        loadingDialog?.show(message)
    }

    protected fun dismissLoading() {
        loadingDialog?.dismiss()
    }


    protected fun startActivity(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    protected fun startActivity(cls: Class<*>, bundle: Bundle) {
        val intent = Intent(this, cls)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun getVersionNameWithCode(): String {
        val packageInfo = getPackageInfo()
        val versionCode = PackageInfoCompat.getLongVersionCode(packageInfo)
        val versionName = packageInfo.versionName
        return "$versionName ($versionCode)"
    }

    private fun getPackageInfo(): PackageInfo {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
        } else {
            packageManager.getPackageInfo(packageName, 0)
        }
    }


    private fun logout(
        activityClass: Class<*>,
        sharedPreferencesHelper: EncryptedSharedPreferencesHelper
    ) {
        sharedPreferencesHelper.clear()
        val intent = Intent(this, activityClass).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finishAffinity()
        exitProcess(0)
    }


    override fun onDestroy() {
        super.onDestroy()
        loadingDialog?.dismiss()
        loadingDialog = null
    }

}
