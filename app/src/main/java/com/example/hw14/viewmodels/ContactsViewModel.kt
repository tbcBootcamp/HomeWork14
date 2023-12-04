package com.example.hw14.viewmodels

import androidx.lifecycle.ViewModel
import com.example.hw14.data.Contact
import com.example.hw14.data.getList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ContactsViewModel : ViewModel() {

    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> get() = _contacts

    fun loadContacts() {
        _contacts.value = getList.toList()
    }

    fun addContact(contact: Contact) {
        getList.add(contact)
        _contacts.value = getList.toList()
    }

    fun updateContact(updatedContact: Contact) {
        val index = getList.indexOfFirst { it.id == updatedContact.id }
        if (index != -1) {
            getList[index] = updatedContact
            _contacts.value = getList.toList()
        }
    }

    fun removeContact(contact: Contact) {
        getList.remove(contact)
        _contacts.value = getList.toList()
    }
}