package com.example

import com.example.db.DBClass

import com.example.repository.userRepository
import com.example.repository.userRepositoryImpl
import com.example.routes.authRoutes
import com.example.security.configSecurity
import com.example.service.UserService
import com.example.service.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.tomcat.*
import org.h2.engine.User


fun main() {
    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0"){
        DBClass.init()
        install(ContentNegotiation){
             jackson()
        }
        configSecurity()

        val service: UserService=UserServiceImpl()
       val repository:userRepository=userRepositoryImpl(service)

        authRoutes(repository)

        routing {
            authenticate {
                get ("/test"){
                    call.respond("working Fine")
                }
            }
        }
    }.start(wait = true)
}

fun Application.module() {
//    configureSerialization()

//    configureDatabases()
//    configureHTTP()
//    configureSecurity()
//    configureRouting()
}
