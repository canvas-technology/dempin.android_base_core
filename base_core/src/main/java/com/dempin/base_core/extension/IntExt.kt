package com.dempin.base_core.extension

fun Int?.ignoreNull(defaultValue: Int = 0): Int = this ?: defaultValue
