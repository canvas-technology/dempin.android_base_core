package com.canvas.demin.pos.extension

import android.annotation.SuppressLint
import android.text.Html
import android.text.Spanned
import java.text.SimpleDateFormat

fun String?.ignoreNull(defaultValue: String = ""): String = this ?: defaultValue

fun String?.toSpanned(): Spanned {
    return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
}


fun String?.getFormattedMoney(): String {
    return try {
        val value = this?.toDouble()
        value.getFormattedMoney()
    } catch (ex: Exception) {
        ""
    }
}


@SuppressLint("SimpleDateFormat")
fun String?.getFormattedDate(): String {
    if (this.isNullOrEmpty()) {
        return ""
    }
    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        inputFormat.parse(this)?.let { date ->
            return outputFormat.format(date)
        }
    } catch (ignored: Exception) {
    }

    return ""
}