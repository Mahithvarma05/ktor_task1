package com.example

import com.example.plugins.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.tomcat.*
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

import io.ktor.server.engine.embeddedServer
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import org.jetbrains.exposed.dao.id.EntityID

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0"){

        install(ContentNegotiation) {
            jackson {}
        }

        install(StatusPages) {
            exception<Throwable> { call: ApplicationCall, cause -> call.respond(HttpStatusCode.InternalServerError, cause.localizedMessage)  }

        }

        DBClass.init()

        routing {
            route("/users") {
                get("/{id}") {
                    val userId = call.parameters["id"]?.toIntOrNull()
                    if (userId == null) {
                        call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
                        return@get
                    }

                    val userWithProfile = transaction {
                        Users.innerJoin(UserProfiles)
                            .select { Users.id eq userId }
                            .map { row -> row.toUser() to row.toUserProfile() }
                            .singleOrNull()
                    }

                    if (userWithProfile != null) {
                        val (user, userProfile) = userWithProfile
                        call.respond(user)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "User not found")
                    }
                }

                post("/") {
                    val user = call.receive<User>()
                    val userProfile = call.receive<UserProfile>()

                    transaction {
                        val userId = Users.insertAndGetId {
                            it[name] = user.name
                        }
                        UserProfiles.insertAndGetId {
                            it[this.userId] = userId.value
                            it[bio] = userProfile.bio
                        }
                    }

                    call.respond(HttpStatusCode.Created)
                }
            }
        }
    }.start(wait = true)
}

data class User(val id: Int, val name: String)

object Users : IntIdTable("userID") {
    val name = varchar("name", 50)
}

fun ResultRow.toUser() = User(this[Users.id].value, this[Users.name])


data class UserProfile(val id: Int, val userId: EntityID<Int>, val bio: String)

object UserProfiles : IntIdTable("userProf") {
    val userId = reference("user_id", Users).uniqueIndex()
    val bio = text("bio")
}

fun ResultRow.toUserProfile() = UserProfile(this[UserProfiles.id].value, this[UserProfiles.userId], this[UserProfiles.bio])



fun Application.module() {
    configureAdministration()
    configureSerialization()
    configureDatabases()
    configureHTTP()
    configureSecurity()
    configureRouting()
}
