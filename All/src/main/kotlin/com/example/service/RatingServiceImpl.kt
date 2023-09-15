package com.example.service

import com.example.db.Customer
import com.example.db.CustomerRatings
import com.example.db.DBClass.dbQuery
import com.example.db.Products
import com.example.db.UserTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.*
import kotlin.math.absoluteValue


class RatingServiceImpl {
     suspend fun rateProduct(Params: ratingParams) {
         dbQuery{
             SchemaUtils.create(UserTable, Products, CustomerRatings)
             val userId:Int = Customer.insert {
                 it[name] = Params.CName
             }get Customer.id


             val productId = Products.insert {
                 it[name] = Params.PName
             }get Products.PId

             CustomerRatings.insert {
                 it[ProductId] = productId
                 it[CustomerId] = userId
                 it[rating] = Params.rating
             }

         }
    }
}