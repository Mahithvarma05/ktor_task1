package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactNumber(
    val number: String,
    val label: String
)