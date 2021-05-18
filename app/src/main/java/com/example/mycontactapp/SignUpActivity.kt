package com.example.mycontactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mycontactapp.db.UserRepository
import com.example.mycontactapp.db.entities.User

class SignUpActivity : AppCompatActivity() {
    private lateinit var fullNameEditText:EditText
    private lateinit var emailAddressEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var signUpLoginButton: Button
    private lateinit var userRepository: UserRepository
    var isExist = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        userRepository = UserRepository(this)
        init()

        signUpButton.setOnClickListener {
            signUpUser()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        signUpLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init(){
        fullNameEditText = findViewById(R.id.full_name_editText)
        emailAddressEditText = findViewById(R.id.signup_emailAddress_editText)
        passwordEditText = findViewById(R.id.signup_password_editText)
        signUpButton = findViewById(R.id.signup_button)
        signUpLoginButton = findViewById(R.id.signupPage_login_button)
    }

    private fun signUpUser(){
        if(validation()){
            userRepository.getAllUsers()?.observe(this, {user ->
                for(i in user.indices){
                    if(user[i].email == fullNameEditText.text.toString().trim() ){
                        isExist = true
                        break
                    } else{
                        isExist = false
                        continue
                    }
                }

            })
            val user = User(
                fullName = fullNameEditText.text.toString().trim(),
                email = emailAddressEditText.text.toString().trim(),
                password = passwordEditText.text.toString().trim()
            )
            userRepository.insertUser(user)
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validation():Boolean{
        if(fullNameEditText.text.toString().trim().isNullOrEmpty()){
            Toast.makeText(this, "Enter Full Name", Toast.LENGTH_SHORT).show()
            return false
        }
        if (emailAddressEditText.text.toString().trim().isNullOrEmpty()){
            Toast.makeText(this, "Enter Email Address", Toast.LENGTH_SHORT).show()
            return false
        }
        if (passwordEditText.text.toString().trim().isNullOrEmpty()){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
            return false
        }
      return true
    }
}