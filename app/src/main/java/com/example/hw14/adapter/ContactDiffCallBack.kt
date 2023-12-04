package com.example.hw14.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.hw14.data.Contact

class ContactDiffCallBack : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean = oldItem == newItem
}