package com.example.aulamvvm.utils.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aulamvvm.utils.room.dao.UserDao
import com.example.aulamvvm.utils.room.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        var appInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if(appInstance == null) {
                appInstance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "list-users-app-database").build()

            }
            return  appInstance as AppDatabase
        }
    }
}