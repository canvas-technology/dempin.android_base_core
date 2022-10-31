package com.dempin.base_core.model

import java.lang.Exception

class ApiResponse<T>(
    var t: T? = null,
    var mStatus: Status = Status.SUCCESS,
    var message: String? = "",
    var mException: Exception? = null,
    var mExtension: Extension? = null
) {
    val status: Status = mStatus
    val errMessage: String? = message
    val data: T? = t
    val exception: Exception? = mException
    val extension: Extension? = mExtension
}