package com.infotechworld.roomdbdemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ContactTbl")
data class ContactDetails(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val phone: String,
)
