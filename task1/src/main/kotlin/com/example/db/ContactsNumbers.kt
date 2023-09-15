package com.example.db

import org.jetbrains.exposed.sql.Table

object ContactsNumbers: Table("ContactsNumbers"){
    val id = ContactsNumbers.integer("id").autoIncrement()
    val CId = reference("cid",Contacts.id)
    val number = varchar("number",10)
    val label = varchar("label",256)
}