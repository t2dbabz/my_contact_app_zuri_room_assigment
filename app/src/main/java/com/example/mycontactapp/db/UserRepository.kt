package com.example.mycontactapp.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mycontactapp.db.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepository(context: Context) {
    private val db = UserDatabase.getDatabase(context)

    fun getAllUsers(): LiveData<List<User>>? = db?.userDao()?.getUserDetails()

    fun insertUser(user: User){
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                db?.userDao()?.insertUserData(user)
            }
        }

    }
}