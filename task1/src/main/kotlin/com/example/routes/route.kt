package com.example.routes

import com.example.model.ContactRequest
import com.example.model.User
import com.example.repository.UserRepository
import com.example.repository.UserRepositoryImpl
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.routing(){

    val repository: UserRepository = UserRepositoryImpl()
    routing {
        post("/createUser") {
            val user = call.receive<User>()
            val result = repository.createUser(user)
            call.respond(result)
        }

        post("/uploadContact") {
            val contactRequest = call.receive<ContactRequest>()
            val result = repository.uploadContacts(contactRequest)

            call.respond(result)
        }

        get("/getAllContacts") {
            val userId = call.parameters["user_id"]
            val result = repository.viewContact(userId.toString())

            call.respond(result)
        }
    }


}