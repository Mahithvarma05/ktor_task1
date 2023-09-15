package com.example.model


import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val firstName: String,
    val lastName: String,
    val contactNumbers: List<ContactNumber>
)
