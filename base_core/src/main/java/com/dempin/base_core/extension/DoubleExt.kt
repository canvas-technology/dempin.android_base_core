package com.dempin.base_core.extension

import java.text.NumberFormat
import java.util.*

fun Double?.getFormattedMoney(): String {
    if (this == null) {
        return ""
    }
    val moneyFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY)
    return moneyFormat.format(this)
}

fun Double?.ignoreNull(defaultValue: Double = 0.0): Double = this ?: defaultValue
