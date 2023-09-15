package com.example.service

import com.example.db.DBClass.dbQuery
import com.example.db.Users3
import com.example.model.User
import org.jetbrains.exposed.sql.insert

class CreateUserService {
     suspend fun addUser(user: User): String {
        val userId : String = dbQuery {
            Users3.insert {
                it[userId] = generateUserId()
                it[userName] = user.userName
                it[mobileNumber] = user.mobileNumber
                it[email] = user.email
            } get Users3.userId
        }
        return userId
    }
    private fun generateUserId(): String {
        return (1..6).map { ("abcdefghijklmnopqrstwxyz1234567890").random() }.joinToString("")
    }

}