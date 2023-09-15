package com.example.db

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


object UserTable:Table("ktor1") {
    val id = integer("id").autoIncrement()
    val name = varchar("name",256)
    val email=varchar("email",256)
    val password=text("password")
    override val primaryKey =PrimaryKey(id)


}
