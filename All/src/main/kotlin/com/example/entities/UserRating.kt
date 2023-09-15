package com.example.entities


import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

//class UserRating(id: EntityID<Int>) : IntEntity(id) {
//    companion object : IntEntityClass<UserRating>(UserRatings)
//    var value by UserRatings.value
//    var productName by Product referencedOn UserRatings.productName
//    var user by Users referencedOn UserRatings.user
//}