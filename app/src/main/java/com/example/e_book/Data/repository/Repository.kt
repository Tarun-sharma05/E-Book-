package com.example.e_book.Data.repository

import android.util.Log
import com.example.e_book.Data.responce.BookModels
import com.example.e_book.Data.responce.bookCategoryModels
import com.example.e_book.ResultState
import com.example.e_book.Ui_layer.Screen.Category
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
                items = snapshot.children.map {value ->
                    value.getValue<BookModels>()!!
                }
                Log.d("Tag", "$items")
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


    suspend fun getBookCategory(): Flow<ResultState<List<bookCategoryModels>>> = callbackFlow{
        trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot){
                var items : List<bookCategoryModels> = emptyList()
                items = snapshot.children.map { value ->
                     value.getValue<bookCategoryModels>()!!
                }
                trySend(ResultState.Success(items))
                }
            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }

        firebaseDatabase.reference.child("BookCategory").addValueEventListener(valueEvent)

        awaitClose{
            firebaseDatabase.reference.removeEventListener(valueEvent)
            close()
        }
    }

    suspend fun getBookByCetogory(category: String): Flow<ResultState<List<BookModels>>> = callbackFlow {
        trySend(ResultState.Loading)
     val valueEvent = object : ValueEventListener {
         override fun onDataChange(snapshot: DataSnapshot) {
             var items: List<BookModels> = emptyList()
             items = snapshot.children.filter {
                 it.getValue<BookModels>()!!.category == category
             }.map {
                 it.getValue<BookModels>()!!
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