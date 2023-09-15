package com.example.plugins

import com.example.Person
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/details"){
            val person=call.receive<Person>()
            println(person)
            call.respond(person)
        }
    }
}
