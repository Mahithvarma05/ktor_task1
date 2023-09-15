package com.example.repository

import com.example.model.ContactRequest
import com.example.model.User
import com.example.utils.BaseResponse

interface UserRepository  {
    suspend fun createUser(user : User):BaseResponse<Any>
    suspend fun uploadContacts(contactRequest: ContactRequest):BaseResponse<Any>
    suspend fun viewContact(userId : String):BaseResponse<Any>

}