package com.example.mycontactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mycontactapp.db.UserRepository
class LoginActivity : AppCompatActivity() {

    private lateinit var loginEmailEditText: EditText
    private lateinit var loginPasswordEditText: EditText
    private lateinit var loginButton:Button
    private lateinit var userRepository: UserRepository
    var isExist = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init(){
        loginEmailEditText = findViewById(R.id.login_emailAddress_editText)
        loginPasswordEditText = findViewById(R.id.login_password_editText)
        loginButton = findViewById(R.id.login_button)
        userRepository = UserRepository(this)

        loginButton.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        if (userLoginValidation()) {
            userRepository.getAllUsers()?.observe(this, { user ->
                for (i in user.indices) {
                    if (user[i].email == loginEmailEditText.text.toString().trim()) {
                        if (user[i].password == loginPasswordEditText.text.toString().trim()) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            Toast.makeText(this, "Password is Incorrect", Toast.LENGTH_SHORT).show()
                        }
                        isExist = true
                        break
                    } else {
                        isExist = false
                    }
                }
                if (isExist) {
                } else {
                    Toast.makeText(this, " User Not Registered ", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun userLoginValidation(): Boolean {
        if (loginEmailEditText.text.toString().isNullOrEmpty()) {
            Toast.makeText(this, "Enter Email Address", Toast.LENGTH_SHORT).show()
            return false
        }
        if (loginPasswordEditText.text.toString().isNullOrEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}