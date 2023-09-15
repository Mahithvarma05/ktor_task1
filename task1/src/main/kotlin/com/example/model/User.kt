package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userName: String,
    val mobileNumber: String,
    val email: String?
)