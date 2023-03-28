package com.infotechworld.roomdbdemo.di

import android.app.Application
import android.content.Context
import com.infotechworld.roomdbdemo.dao.ContactDao
import com.infotechworld.roomdbdemo.database.ContactDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getDB(context: Application): ContactDataBase{
        return ContactDataBase.roomInstance(context)
    }

    @Singleton
    @Provides
    fun getDAO(dataBase: ContactDataBase): ContactDao{
        return dataBase.contactDao()
    }
}