package com.example.mycontactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mycontactapp.adapter.ContactListAdapter
import com.example.mycontactapp.databinding.ActivityContactListBinding
import com.example.mycontactapp.model.Contact

class ContactListActivity : AppCompatActivity() {
    companion object {
        const val CATEGORY = "category"
    }


    private lateinit var binding: ActivityContactListBinding
    private val adapter = ContactListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter(binding)
    }


    fun setupAdapter(binding: ActivityContactListBinding) {
        val letterId = intent?.extras?.getString(CATEGORY).toString()
        title = letterId
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        builder.setView(view)

        val name = view.findViewById<TextView>(R.id.nameTextEditText)
        val number = view.findViewById<TextView>(R.id.numberTextEditText)
        val saveButton = view.findViewById<Button>(R.id.contactSaveButton)
        val alertDialog = builder.create()
        number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                saveButton.isEnabled = s?.length == 11
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.fab.setOnClickListener {
            alertDialog.show()
        }

        saveButton.setOnClickListener {
            val contact = Contact(name.text.toString(), number.text.toString())
            val contacts = mutableListOf(contact)
            adapter.setupContactData(contacts)
            alertDialog.dismiss()
        }
    }
}