package com.example.e_book.di

import com.example.e_book.Data.repository.Repository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideFirebaseDatabase() : FirebaseDatabase{
         return FirebaseDatabase.getInstance()
    }

    @Singleton
    @Provides
    fun provideRepo(firebaseDatabase: FirebaseDatabase) : Repository{
        return Repository(firebaseDatabase)
    }

}