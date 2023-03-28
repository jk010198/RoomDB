package com.infotechworld.roomdbdemo.repository

import androidx.lifecycle.LiveData
import com.infotechworld.roomdbdemo.dao.ContactDao
import com.infotechworld.roomdbdemo.model.ContactDetails
import javax.inject.Inject

class ContactRepo @Inject constructor(private val dao: ContactDao) {

    suspend fun insertContact(contactDetails: ContactDetails){
        dao.insertContact(contactDetails)
    }

    suspend fun updateContact(contactDetails: ContactDetails){
        dao.updateContact(contactDetails)
    }

    suspend fun deleteContact(contactDetails: ContactDetails){
        dao.deleteContact(contactDetails)
    }

    fun getAllContacts() :LiveData<List<ContactDetails>>{
        return dao.getAllContacts()
    }
}