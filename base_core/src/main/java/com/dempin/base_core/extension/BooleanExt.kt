package com.dempin.base_core.extension

fun Boolean?.ignoreNull(defaultValue: Boolean = false): Boolean = this ?: defaultValue
