package com.example.service

import com.example.db.Contacts
import com.example.db.ContactsNumbers
import com.example.db.DBClass.dbQuery
import com.example.model.ContactData
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select

class ViewContactsService {
    suspend fun searchByUserId(userId: String): Any? {
        val result:Any? = dbQuery {
            val data:Any? =
                (Contacts innerJoin ContactsNumbers)
                    .slice(
                        Contacts.id,
                        Contacts.userId,
                        Contacts.firstName,
                        Contacts.lastName,
                        ContactsNumbers.number,
                        ContactsNumbers.label
                    )
                    .select {
                        Contacts.userId eq userId
                    }
                    .map {
                        val contactId = it[Contacts.id]
                        val userid = it[Contacts.userId]
                        val firstName = it[Contacts.firstName]
                        val lastName = it[Contacts.lastName]
                        val number = it[ContactsNumbers.number]
                        val label = it[ContactsNumbers.label]

                        ContactData(contactId, userid, firstName, lastName, number, label)
                    }
            data


        }
        return if (result==null) null
        else result


    }
}