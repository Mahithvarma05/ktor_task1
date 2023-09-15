package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactRequest(val userId: String, val contacts: List<Contact>)