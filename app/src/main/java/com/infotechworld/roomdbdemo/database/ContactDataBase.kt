package com.infotechworld.roomdbdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.infotechworld.roomdbdemo.dao.ContactDao
import com.infotechworld.roomdbdemo.model.ContactDetails

@Database(entities = [ContactDetails::class], version = 1, exportSchema = false)
abstract class ContactDataBase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var instance: ContactDataBase? = null

        fun roomInstance(context: Context): ContactDataBase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        ContactDataBase::class.java,
                        "PhoneBookDB").build()
                }
            }
            return instance!!
        }
    }
}