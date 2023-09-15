package com.example.repository

import com.example.service.CreateUserParams
import com.example.utils.BaseResponse

interface userRepository {
    suspend fun registerUser(params: CreateUserParams):BaseResponse<Any>
    suspend fun loginUser(email:String,password:String):BaseResponse<Any>
}