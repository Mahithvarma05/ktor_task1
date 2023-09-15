package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactData(
    val contactId:Int,
    val  userId:String,
    val firstName:String,
    val lastName:String,
    val number:String,
    val label:String
)