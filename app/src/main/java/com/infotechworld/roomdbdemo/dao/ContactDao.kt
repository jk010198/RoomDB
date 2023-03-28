package com.infotechworld.roomdbdemo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.infotechworld.roomdbdemo.model.ContactDetails

@Dao
interface ContactDao {

    @Insert
    suspend fun insertContact(contactDetails: ContactDetails)

    @Update
    suspend fun updateContact(contactDetails: ContactDetails)

    @Delete
    suspend fun deleteContact(contactDetails: ContactDetails)

    @Query("select * from ContactTbl")
    fun getAllContacts(): LiveData<List<ContactDetails>>
}