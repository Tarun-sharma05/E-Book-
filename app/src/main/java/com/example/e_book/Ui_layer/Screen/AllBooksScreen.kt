package com.example.e_book.Ui_layer.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_book.ViewModel.AppViewModel


@Composable
fun AllBooksScreen(viewModel: AppViewModel = hiltViewModel()) {

    val state = viewModel.getAllBooksState.collectAsState()
    val data = state.value.data?: emptyList()

    when{
        state.value.isLoading -> {
        }
        state.value.Error != null -> {

        }
        state.value.data != null -> {

            Column(
                modifier = Modifier.fillMaxSize()
            ){
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(data) {

                    }
                }

            }
        }
    }
}

@Composable
fun books(){}