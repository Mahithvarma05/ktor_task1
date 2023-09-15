package com.example.repository

import com.example.model.ContactRequest
import com.example.model.User
import com.example.service.CreateUserService
import com.example.service.UploadContactService
import com.example.service.ViewContactsService
import com.example.utils.BaseResponse

class UserRepositoryImpl:UserRepository {

    override suspend fun createUser(user: User): BaseResponse<Any> {

        val userId = CreateUserService().addUser(user)

        return if (isPhoneNumberValid(user.mobileNumber)) BaseResponse.SuccessResponse(data = userId)

        else BaseResponse.ErrorResponse(message = "Mobile number is invalid")

    }

    override suspend fun uploadContacts(contactRequest: ContactRequest): BaseResponse<Any> {

        val msg = UploadContactService().upload(contactRequest)

        return BaseResponse.SuccessResponse(message = msg)

    }

    override suspend fun viewContact(userId: String): BaseResponse<Any> {

        val contactData:Any? = ViewContactsService().searchByUserId(userId)

        return if (contactData == null) BaseResponse.ErrorResponse(message = "User not found")

        else BaseResponse.SuccessResponse(data = contactData)
    }
    private  fun isPhoneNumberValid(mobileNumber: String): Boolean {

        val number = mobileNumber.replace(Regex("[^0-9]"), "")

        return number.length == 10
    }
}