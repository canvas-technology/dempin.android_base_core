package com.dempin.base_core.extension

import android.widget.EditText

fun EditText?.getString(): String = this?.text?.trim().toString()