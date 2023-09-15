package com.example.routes

import com.example.repository.userRepository
import com.example.service.CreateUserParams
import com.example.service.RatingServiceImpl
import com.example.service.ratingParams
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun  Application.authRoutes(repository: userRepository){
    routing {
        route("/auth"){
            post("/register"){
                val params = call.receive<CreateUserParams>()
                val result = repository.registerUser(params)
                call.respond(result)
            }
            post ("/rating"){
                val params = call.receive<ratingParams>()

                RatingServiceImpl().rateProduct(params)
                call.respond("Done")

            }

        }
    }
}