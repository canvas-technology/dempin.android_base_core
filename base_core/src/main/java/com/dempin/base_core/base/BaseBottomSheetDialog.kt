package com.dempin.base_core.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.ViewDataBinding
import com.dempin.base_core.R
import com.dempin.base_core.extension.ignoreNull
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog<VDB : ViewDataBinding>(private var isFull: Boolean? = false) :
    BottomSheetDialogFragment() {

    protected lateinit var binding: VDB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewDataBinding(inflater, container)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener { masterDialog ->
            val dialog = masterDialog as BottomSheetDialog
            dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                if(isFull.ignoreNull()){
                    setupFullHeight(it)
                }
                behavior.skipCollapsed = true
                behavior.setHideable(true)
            }
        }
        return bottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.let {
            WindowCompat.setDecorFitsSystemWindows(it, false)
            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
                val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                binding.root.setPadding(0, 0, 0, imeHeight)
                insets
            }
        }
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    override fun getTheme() = R.style.CustomBottomSheetDialogTheme

    protected abstract fun getViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): VDB

}