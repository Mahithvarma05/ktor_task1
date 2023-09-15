package com.example.service

import com.example.db.Contacts
import com.example.db.ContactsNumbers
import com.example.db.DBClass.dbQuery
import com.example.model.ContactRequest
import org.jetbrains.exposed.sql.insert

class UploadContactService {
    suspend fun upload(contactRequest: ContactRequest):String{
        val contacts = contactRequest.contacts

        dbQuery {

            for (contact in contacts) {
                val cid = Contacts.insert {
                    it[userId] = contactRequest.userId
                    it[firstName] = contact.firstName
                    it[lastName] = contact.lastName
                } get Contacts.id

                for (contactData in contact.contactNumbers) {

                    ContactsNumbers.insert {
                        it[CId] = cid
                        it[number] = contactData.number
                        it[label] = contactData.label

                    }
                }
            }
        }
        return "Contacts uploaded successfully."

    }
}