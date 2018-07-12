package com.sedmandev.callmenow.api.common

data class Error(
    val status: Boolean,
    val message: String?,
    val field: Any?
)