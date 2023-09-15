package com.example.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class jwtCong private constructor(secret:String){

    private val algorithm = Algorithm.HMAC256(secret)

    companion object{

        private const val ISSUER ="myData"
        private const val AUDIENCE="myData"
        const val CLAIM = "id"


        lateinit var instance: jwtCong


            private set

        fun initialize(secret: String){
            synchronized(this){
                
                if (!(this::instance.isInitialized)){
                    instance=jwtCong(secret)
                }
            }
        }
    }

    val verifier:JWTVerifier = JWT.require(algorithm).withIssuer(ISSUER).withAudience(AUDIENCE).build()

    fun createAccessToken(id:Int):String=JWT.create().withIssuer(ISSUER).withAudience(AUDIENCE).withClaim(CLAIM,id).sign(algorithm)

}