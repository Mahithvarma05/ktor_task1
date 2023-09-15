package com.example

import com.example.plugins.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.tomcat.*

fun main() {
    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0"){



        homeRoute()

//        install(Routing){
////            toYou()
//            get ("/"){
//                println("URI${call.request.uri}")
//                println("Headers${call.request.headers.names()}")
//            }
//            get("/book/{page}"){
//                val num=call.parameters["page"]
//                call.respondText("You are on page number: $num")
//            }
//            post ("/details"){
//               val details= call.receive<Details>()
//                call.respondText("id:${details.email} pass:${details.password}")
//            }
//
//        }

    }

        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    homeRoute()


}
fun Routing.toYou(){
    get ("/toYou"){
        call.respond("ThankYou")
    }
}

fun Application.homeRoute(){
    routing {
        get("/") {
            call.respond("Hello Mahithb")
        }
        get ("/toYou"){
            call.respond("ThankYou")
        }
        get ("/toMe"){
            call.respond("Wonder full")
        }
        get("/book/{page}"){
            val num=call.parameters["page"]
            call.respondText("You are on page number: $num")
        }
        post ("/details"){
            val details= call.receive<Details>()
            call.respondText("id:${details.email} pass:${details.password}")
        }

    }
}
