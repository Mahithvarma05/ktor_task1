package com.example.security

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val KEY="84516542"
private val ALGORITHM="hmacSHA1"

val HASH_KEY=hex(KEY)
val HMAC_KEY= SecretKeySpec(HASH_KEY, ALGORITHM)

fun hash(password:String):String{
    val hmac= Mac.getInstance(ALGORITHM)
    hmac.init(HMAC_KEY)
    return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}

