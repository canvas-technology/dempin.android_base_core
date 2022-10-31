package com.dempin.base_core.model

open class BaseResponse<T> (
    val status:Boolean?,
    val message: String?,
    val data:T
)