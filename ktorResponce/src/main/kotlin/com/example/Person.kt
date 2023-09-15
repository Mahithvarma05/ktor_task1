package com.example

import kotlinx.serialization.Serializable

@Serializable
data class Person (val email:String,val password:String){
}