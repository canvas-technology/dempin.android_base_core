package com.dempin.base_core.extension

fun Char?.ignoreNull(defaultValue: Char = Character.MIN_VALUE) = this ?: defaultValue
