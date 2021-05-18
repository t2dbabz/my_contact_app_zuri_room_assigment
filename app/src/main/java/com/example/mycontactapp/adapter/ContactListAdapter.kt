package com.example.mycontactapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontactapp.model.Contact
import com.example.mycontactapp.databinding.ContactListItemBinding

class ContactListAdapter() : RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {
    private val contactData = mutableListOf<Contact>(
        Contact("Tunde", "12345678912"),
        Contact("Betty", "12355464677"),
    )

    class ContactListViewHolder(private val binding: ContactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(contact: Contact) {
            binding.nameTextView.text = contact.name
            binding.phoneNumberTextView.text = contact.number
        }
    }

    fun setupContactData(contact: List<Contact>) {
        contactData.addAll(contact)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val adapterLayout = ContactListItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ContactListViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        val contact = contactData[position]
        holder.bindItem(contact)
    }

    override fun getItemCount(): Int {
        return contactData.size
    }
}