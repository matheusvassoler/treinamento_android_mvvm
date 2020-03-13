package com.example.aulamvvm.utils.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserEntity (
    @PrimaryKey
    var userId: Long,
    var name: String,
    var address: String,
    var imgUrl: String,
    var login: String,
    var isLogged: Boolean
)