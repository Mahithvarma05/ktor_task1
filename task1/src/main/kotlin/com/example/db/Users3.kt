package com.example.db

import org.jetbrains.exposed.sql.Table

object Users3 : Table("Users3") {
    val id = Users3.integer("id").autoIncrement()
    val userId = varchar("userId",256)
    val userName = varchar("user_name", 256)
    val mobileNumber = varchar("mobile_number", 10)
    val email = varchar("email", 256).nullable()
    override val primaryKey =PrimaryKey(id)
}