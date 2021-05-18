package com.example.mycontactapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mycontactapp.db.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUserDetails(): LiveData<List<User>>

    @Insert
    fun insertUserData(user: User)

}