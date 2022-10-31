package com.dempin.base_core.extension


import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.canvas.demin.pos.model.ApiResponse
import com.canvas.demin.pos.model.Status

fun <T> ApiResponse<T>.handleWithErrorView(
    errorTextView: AppCompatTextView,
    mainView: View? = null,
    block: (T) -> Unit
) {
    when (this.status) {
        Status.SUCCESS -> {
            if (this.data != null) {
                errorTextView.visibility = View.GONE
                mainView?.visibility = View.VISIBLE
                block.invoke(this.data)
            } else {
                errorTextView.visibility = View.VISIBLE
                mainView?.visibility = View.GONE
            }
        }
        Status.ERROR -> {
            errorTextView.visibility = View.VISIBLE
            mainView?.visibility = View.GONE
            if (!this.errMessage.isNullOrEmpty()) {
                errorTextView.text = this.errMessage
            }
        }
        else -> {
            errorTextView.visibility = View.VISIBLE
            mainView?.visibility = View.GONE
        }
    }
}

fun <T> ApiResponse<T>.handleWithErrorViewAndErrorCallback(
    errorTextView: AppCompatTextView,
    mainView: View? = null,
    successCallBack: (T) -> Unit,
    errorCallBack: () -> Unit,
) {
    when (this.status) {
        Status.SUCCESS -> {
            if (this.data != null) {
                successCallBack.invoke(this.data)
                errorTextView.visibility = View.GONE
                mainView?.visibility = View.VISIBLE
            } else {
                errorTextView.visibility = View.VISIBLE
                mainView?.visibility = View.GONE
            }
        }
        Status.ERROR -> {
            errorTextView.visibility = View.VISIBLE
            mainView?.visibility = View.GONE
            if (!this.errMessage.isNullOrEmpty()) {
                errorTextView.text = this.errMessage
            }
            errorCallBack.invoke()
        }
        else -> {
            errorTextView.visibility = View.VISIBLE
            mainView?.visibility = View.GONE
            errorCallBack.invoke()
        }
    }
}
