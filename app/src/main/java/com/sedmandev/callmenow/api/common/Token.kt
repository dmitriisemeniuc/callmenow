package com.sedmandev.callmenow.api.common

import com.google.gson.annotations.SerializedName

data class Token (
    @SerializedName("token") val token: String,
    @SerializedName("role") val role: String,
    @SerializedName("user_id") val userId: Int
)
