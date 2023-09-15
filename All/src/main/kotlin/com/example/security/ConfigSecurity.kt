package com.example.security

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*


fun Application.configSecurity(){
    jwtCong.initialize("myData")
    install(Authentication){
        jwt {
            verifier(jwtCong.instance.verifier)
            validate {
                val claim = it.payload.getClaim(jwtCong.CLAIM).asInt()
                if (claim != null){
                    UserIdPrincipalForUser(claim
                    )
                }else{
                    null
                }
            }
        }
    }
}