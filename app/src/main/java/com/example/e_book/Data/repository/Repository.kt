package com.example.e_book.Data.repository

import com.example.e_book.Data.responce.BookModels
import com.example.e_book.ResultState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(private val firebaseDatabase: FirebaseDatabase) {

    suspend fun getAllBooks(): Flow<ResultState<List<BookModels>>> = callbackFlow{
         trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items : List<BookModels> = emptyList()
                snapshot.children.map {value ->
                    value.getValue<BookModels>()!!
                }
                trySend(ResultState.Success(items))
            }
            override fun onCancelled(error: DatabaseError) {
                  trySend(ResultState.Error(error.toException()))
            }

        }

        firebaseDatabase.reference.child("Books").addValueEventListener(valueEvent)

        awaitClose{
            firebaseDatabase.reference.removeEventListener(valueEvent)
            close()
        }
    }
}