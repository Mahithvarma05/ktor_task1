package com.example.repository

import com.example.security.jwtCong
import com.example.service.CreateUserParams
import com.example.service.UserService
import com.example.utils.BaseResponse

class userRepositoryImpl(private val userService:UserService) : userRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (isEmailExist(params.email)){
            BaseResponse.ErrorResponse(message = "Email is already exist")
        }else{
            val user = userService.registerUser(params)
            if (user != null){
                val token = jwtCong.instance.createAccessToken(user.id)
                user.authToken=token
                BaseResponse.SuccessResponse(data=user)
            } else{
                BaseResponse.ErrorResponse()
            }
        }

    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }
    private suspend fun isEmailExist(email: String):Boolean{
        return userService.findUserByEmail(email)!=null
    }
}