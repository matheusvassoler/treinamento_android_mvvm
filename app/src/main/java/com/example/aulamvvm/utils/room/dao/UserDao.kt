package com.example.aulamvvm.utils.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aulamvvm.utils.room.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(user: UserEntity): Long

    @Query("SELECT * FROM userentity WHERE isLogged = 1")
    fun logedUser(): UserEntity


}