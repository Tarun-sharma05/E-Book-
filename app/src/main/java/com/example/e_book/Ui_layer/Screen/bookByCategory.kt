package com.example.e_book.Ui_layer.Screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.e_book.Ui_layer.Navigation.Routs
import com.example.e_book.ViewModel.AppViewModel

@Composable
fun BookByCategory(viewModel: AppViewModel = hiltViewModel(), navController: NavController, category: String) {
      val state  = viewModel.getBookByCategoryState.collectAsState()
    val data = state.value.data?: emptyList()

    LaunchedEffect(key1 = Unit) {
        viewModel.getBookByCategory(category)
    }

    when{
        state.value.isLoading ->{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
            Log.d("Tag", "Loading: ${state.value.isLoading}")
        }
        state.value.Error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Books Not Available")
            }
        }
        state.value.data != null -> {
            Column {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(data){
                        Books(
                            title = it.booksName,
                            url = it.bookUrl,
                            bookImage = it.bookImage,
                            author = it.bookAuthor,
                            onItemClick = {
                                navController.navigate(Routs.pdfView(it.bookUrl))
                            }
                        )

                    }
                }
            }
        }
    }
}


