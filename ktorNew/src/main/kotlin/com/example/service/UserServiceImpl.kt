package com.example.service

import com.example.db.DBClass.dbQuery
import com.example.db.UserTable
import com.example.model.User
import com.example.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement


class UserServiceImpl : UserService {
    override suspend fun registerUser(params: CreateUserParams): User? {
        var statement: InsertStatement<Number>?=null
        dbQuery {
            statement=UserTable.insert {
                it[name]=params.name
                it[email]=params.email
                it[password]=hash(params.password)
            }
        }
        return userRow(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String): User? {
        val user = dbQuery {
            UserTable.select{UserTable.email.eq(email)}
                .map { userRow(it) }.singleOrNull()
        }
        return  user
    }
    private fun userRow(row: ResultRow?):User?{
        return if(row == null) null
        else User(
            id = row[UserTable.id],
            name = row[UserTable.name],
            email = row[UserTable.email]
        )
    }
}