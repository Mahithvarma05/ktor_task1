package com.example.db

import org.jetbrains.exposed.sql.Table

object Contacts : Table("Contacts") {
    val id = Contacts.integer("id").autoIncrement()
    val userId = varchar("userid",256)
    val firstName = varchar("first_name", 256)
    val lastName = varchar("last_name", 256)
    override val primaryKey =PrimaryKey(id)
}