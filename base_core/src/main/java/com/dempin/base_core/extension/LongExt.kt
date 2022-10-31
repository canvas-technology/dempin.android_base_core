package com.dempin.base_core.extension

fun Long?.ignoreNull(defaultValue: Long = 0L): Long = this ?: defaultValue
