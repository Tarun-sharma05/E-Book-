package com.example.booknest.Ui_layer.Screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.booknest.Ui_layer.Navigation.Routs
import com.example.booknest.ViewModel.AppViewModel
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookByCategory(viewModel: AppViewModel = hiltViewModel(), navController: NavController, category: String) {
    val state = viewModel.getBookByCategoryState.collectAsState()
    val data = state.value.data ?: emptyList()

    LaunchedEffect(key1 = Unit) {
        viewModel.getBookByCategory(category)
    }

    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier.padding(),
            navigationIcon = {Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)},
            title = {Text(text = category)})
       }) { paddingValue ->
    when {
        state.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
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
            Column(modifier = Modifier.padding(paddingValue)) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(data) {
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
}


