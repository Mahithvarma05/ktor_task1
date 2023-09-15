package com.example

import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select

fun main() {
    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}
object StarWarsFilms : IntIdTable() {
    val sequelId: Column<Int> = integer("sequel_id").uniqueIndex()
    val name: Column<String> = varchar("name", 50)
    val director: Column<String> = varchar("director", 50)
}
val id = StarWarsFilms.insertAndGetId {
    it[name] = "The Last Jedi"
    it[sequelId] = 8
    it[director] = "Rian Johnson"
}
val query: Query = StarWarsFilms.select { StarWarsFilms.sequelId eq 8 }

val s =StarWarsFilms.select { StarWarsFilms.sequelId eq 8 }.forEach {
    println(it[StarWarsFilms.name])
}

//StarWarsFilms.select{ StarWarsFilms.sequelId eq 8 }.forEach {
//    println(it[StarWarsFilms.name])
//}

fun Application.module() {
    configureSockets()
    configureSerialization()
    configureDatabases()
    configureSecurity()
    configureRouting()
}
