package com.example.booknest.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booknest.Data.repository.Repository
import com.example.booknest.Data.responce.BookModels
import com.example.booknest.Data.responce.bookCategoryModels
import com.example.booknest.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _getAllBooksState = MutableStateFlow(GetAllBooksState())
    val getAllBooksState = _getAllBooksState.asStateFlow()

    private val _getAllBooksCategoryState = MutableStateFlow(GetAllBooksCategoryState())
    val getAllBooksCategoryState = _getAllBooksCategoryState.asStateFlow()

    private val _getBookByCategoryState = MutableStateFlow(GetBookByCategoryState())
    val getBookByCategoryState = _getBookByCategoryState.asStateFlow()

    fun getAllBooks() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllBooks().collect{
                when(it){
                    is ResultState.Loading -> {
                        Log.e("AppViewModel", "Loading books: ")
                        _getAllBooksState.value = GetAllBooksState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        Log.d("AppViewModel", "Fetched books: ${it.data}")
                        _getAllBooksState.value = GetAllBooksState(isLoading = false, data = it.data)
                    }
                    is ResultState.Error -> {
                        Log.e("AppViewModel", "Error fetching books: ${it.exception}")
                        _getAllBooksState.value = GetAllBooksState(isLoading = false, Error = it.exception)
                    }
                }
            }
        }
    }

    fun getAllBooksCategory(){
        viewModelScope.launch(Dispatchers.IO){

            repository.getBookCategory().collect{
                when(it){
                    is ResultState.Loading -> {
                        _getAllBooksCategoryState.value = GetAllBooksCategoryState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _getAllBooksCategoryState.value = GetAllBooksCategoryState(isLoading = false, data = it.data)
                    }

                    is ResultState.Error -> {
                        _getAllBooksCategoryState.value = GetAllBooksCategoryState(isLoading = false, Error = it.exception)
                    }
                }
            }
        }
    }

    fun getBookByCategory(category: String){
        viewModelScope.launch (Dispatchers.IO){
            repository.getBookByCetogory(category).collect{
                when(it){
                    is ResultState.Loading -> {
                        _getBookByCategoryState.value = GetBookByCategoryState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _getBookByCategoryState.value = GetBookByCategoryState(isLoading = false, data = it.data)
                    }

                    is ResultState.Error -> {
                        _getBookByCategoryState.value = GetBookByCategoryState(isLoading = false, Error = it.exception)
                    }
                }
            }
        }
    }
}

data class GetAllBooksState(
    var isLoading: Boolean = false,
    var data : List<BookModels> = emptyList(),
    var Error : Throwable? = null
)

data class GetAllBooksCategoryState(
    var isLoading: Boolean = false,
    var data: List<bookCategoryModels> = emptyList(),
    var Error: Throwable? = null
)

data class GetBookByCategoryState(
    var isLoading: Boolean = false,
    var data: List<BookModels> = emptyList(),
    var Error: Throwable? = null
)