package com.example.entities

import com.example.db.UserTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

//class Users(id: EntityID<Int>) : IntEntity(id) {
//    companion object : IntEntityClass<Users>(UserTable)
//    var sequelId by UserTable.sequelId
//    var name by UserTable.name
//    var email by UserTable.email
//    val password by UserTable.password
//}