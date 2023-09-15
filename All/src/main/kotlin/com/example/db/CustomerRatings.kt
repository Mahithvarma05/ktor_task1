package com.example.db

import org.jetbrains.exposed.sql.Table

object CustomerRatings : Table("CustomerRatings") {
    val RId = integer("RId").autoIncrement()
    val ProductId = reference("productId", Products.PId)
    val CustomerId = reference("name", Customer.id)
    val rating = integer("Rating")
    override val primaryKey =PrimaryKey(RId)
}