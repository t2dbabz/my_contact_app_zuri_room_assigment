package com.example.mycontactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycontactapp.adapter.CategoryAdapter
import com.example.mycontactapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter(binding)
    }

    fun setupAdapter(binding: ActivityMainBinding) {
        val categoryList = listOf("Family", "Colleagues", "Friend", "Business", "Tutors", "Customers")
         val adapter = CategoryAdapter(categoryList)
        binding.categoryRecyclerView.adapter = adapter
    }
}