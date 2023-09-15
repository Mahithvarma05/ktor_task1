package com.example.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

object Products : Table("Products") {
    val PId = integer("Pid").autoIncrement()
    val name = varchar("name", 50)
    override val primaryKey = PrimaryKey(PId)

}