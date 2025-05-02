package com.example.e_book.Ui_layer.Screen

import android.graphics.ColorSpace.Model
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.e_book.Ui_layer.Navigation.Routs
import com.example.e_book.ViewModel.AppViewModel

@Composable
fun Category(viewModel: AppViewModel = hiltViewModel(), navController: NavController) {

     val state = viewModel.getAllBooksCategoryState.collectAsState()
    val data = state.value.data?: emptyList()

    LaunchedEffect (key1 = Unit){
        viewModel.getAllBooksCategory()
    }

    when {
        state.value.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        state.value.Error != null -> {
            Text(text = "Category is not available")
        }
        state.value.data != null ->{

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(data){
                        categories(
                            categoryImage = it.categoryImageUrl,
                            categoryName = it.Name,
                            navController = navController

                        )
//                        Text(text = it.Name, modifier = Modifier.padding(10.dp).clickable {
//                                navController.navigate(Routs.BookByCategory(category = it.Name))
//                        })
                    }
                }
            }

        }
    }
}

@Composable
fun categories(
     categoryName: String,
     categoryImage: String,
     navController: NavController

) {

    Card(modifier = Modifier.fillMaxWidth()
        .height(110.dp)
        .padding(10.dp)
        .clickable {
          navController.navigate(Routs.BookByCategory(category = categoryName))
        }
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = White, shape = shape),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = categoryImage,
                    contentDescription = categoryName,
                    modifier = Modifier
                        //.padding(start = 8.dp)
                        .fillMaxSize()
                        .aspectRatio(.25f)
                        .blur(radiusY = 2.dp, radiusX = 2.dp)

                )
                Text(
                    text = categoryName, Modifier.padding(start = 10.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Black,
                    style = MaterialTheme.typography.titleMedium

                )
            }

    }
}