package com.dempin.base_core.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long?.ignoreNull(defaultValue: Long = 0L): Long = this ?: defaultValue

fun Long?.getFormattedDate(
    format: String,
    locale: Locale = Locale.getDefault(),
    timeZone: TimeZone? = null
): String {
    if (this == null || this == 0L) {
        return ""
    }
    return try {
        val formatter = SimpleDateFormat(format, locale)
        if (timeZone != null) {
            formatter.timeZone = timeZone
        }
        val date = Date(this)
        formatter.format(date)
    } catch (ex: Exception) {
        ""
    }
}

