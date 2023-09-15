package com.example.db

import com.example.db.UserTable.autoIncrement
import org.jetbrains.exposed.sql.Table

object Customer: Table("Customer") {
    val id = Customer.integer("id").autoIncrement()
    val name = Customer.varchar("name", 256)
    override val primaryKey =PrimaryKey(id)
}