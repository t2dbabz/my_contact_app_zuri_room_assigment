package com.example.mycontactapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycontactapp.db.dao.UserDao
import com.example.mycontactapp.db.entities.User


@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase:RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var instance: UserDatabase? = null

        fun getDatabase(context:Context): UserDatabase?{

            if(instance == null) synchronized(UserDatabase::class.java){
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        UserDatabase::class.java,
                        "USER_DATABASE"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}