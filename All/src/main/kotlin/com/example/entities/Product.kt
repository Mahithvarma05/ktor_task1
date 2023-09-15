package com.example.entities

import com.example.db.Products

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

//class Product(id: EntityID<Int>) : IntEntity(id) {
//    companion object : IntEntityClass<Product>(Products)
//    var sequelId by Products.sequelId
//    var name     by Products.name
//    val ratings by UserRating referrersOn UserRatings.productName
//
//}