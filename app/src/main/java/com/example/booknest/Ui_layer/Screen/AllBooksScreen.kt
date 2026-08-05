package com.example.book_nest.Ui_layer.Screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.book_nest.Ui_layer.Navigation.Routs
import com.example.book_nest.ViewModel.AppViewModel


@Composable
fun AllBooksScreen(viewModel: AppViewModel = hiltViewModel(), navController: NavController) {

    val state = viewModel.getAllBooksState.collectAsState()
    val data = state.value.data?: emptyList()

    LaunchedEffect(key1 = Unit){
        viewModel.getAllBooks()
    }

    when {
        state.value.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }

        }

        state.value.Error != null -> {
            Text(text = "No Book available", modifier = Modifier.fillMaxWidth(),
                color = Color.Red
                )
        }

        state.value.data != null -> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items ( data){book->
                      Books(
                          title = book.booksName,
                          url = book.bookUrl,
                          bookImage = book.bookImage,
                          author = book.bookAuthor,
                          onItemClick = {
                              navController.navigate(Routs.pdfView(book.bookUrl))
                          }
                      )
                    }

                }

            }
        }
    }

//        Column(
//            modifier = Modifier.fillMaxSize()
//        ){
//            LazyColumn(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                items(5) {
//                    books(
//                        title = "Kotlin",
//                        url = "Bookurl",
//                        bookImage = "Image",
//                        author = "Tarun"
//                    )
//
//                }
//
//
//
//            }
//
//        }


}


@Composable
fun Books(
    title: String,
    url: String,
    bookImage: String,
    author: String,
    onItemClick: () -> Unit = {}

){
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
        //    .clickable { onItemClick() }
        ,
        onClick = {
//              Card(modifier = Modifier.fillMaxSize()) { }
            onItemClick()

        }
    ) {
           Row(){
               Column {
                   AsyncImage(
                       model = bookImage,
                       contentDescription = title,
                       modifier = Modifier.size(100.dp)
                   )
               }

               Spacer(modifier = Modifier.width(16.dp))

              Column {
                  Text(
                      text = title,
                      fontSize = 16.sp,
                      fontFamily = FontFamily.SansSerif,
                      fontWeight = FontWeight.ExtraBold
                  )

                  Spacer(modifier = Modifier.height(5.dp))

                  Text(
                      text = author,
                      fontSize = 10.sp,
                      fontFamily = FontFamily.SansSerif,
                      fontWeight = FontWeight.Normal
                  )
              }
           }

    }

}