package com.dempin.base_core.model

enum class Status (val type: Int) {
    SUCCESS(type = 1),
    ERROR(type = 0),
    DATA_NOT_FOUND(type = 2),
    ERROR_HTTP_CONFIRMATION(type = 3)
}