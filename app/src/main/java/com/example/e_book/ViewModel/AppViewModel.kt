package com.example.e_book.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book.Data.repository.Repository
import com.example.e_book.Data.responce.BookModels
import com.example.e_book.ResultState
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

    fun getAllBooks() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllBooks().collect{
                when(it){
                    is ResultState.Loading -> {
                        _getAllBooksState.value = GetAllBooksState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _getAllBooksState.value = GetAllBooksState(isLoading = false, data = it.data)
                    }
                    is ResultState.Error -> {
                        _getAllBooksState.value = GetAllBooksState(isLoading = false, Error = it.exception)
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