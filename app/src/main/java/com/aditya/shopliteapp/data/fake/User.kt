package com.aditya.shopliteapp.data.fake

data class User (
    val id : Int,
    val username : String,
    val email : String,
    val firstName : String,
    val lastName : String,
    val image : String
)
{
    val fullName : String
        get() = "$firstName $lastName"
}