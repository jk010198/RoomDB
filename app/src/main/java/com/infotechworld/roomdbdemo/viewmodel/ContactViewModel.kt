package com.infotechworld.roomdbdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infotechworld.roomdbdemo.model.ContactDetails
import com.infotechworld.roomdbdemo.repository.ContactRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val contactRepo: ContactRepo) : ViewModel() {

    fun insertContact(contactDetails: ContactDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepo.insertContact(contactDetails)
        }
    }

    fun updateContact(contactDetails: ContactDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepo.updateContact(contactDetails)
        }
    }

    fun deleteContact(contactDetails: ContactDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepo.deleteContact(contactDetails)
        }
    }

    fun getContacts(): LiveData<List<ContactDetails>> {
        return contactRepo.getAllContacts()
    }

}