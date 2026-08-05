package com.example.booknest.Ui_layer.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.booknest.Ui_layer.Screen.BookByCategory
import com.example.booknest.Ui_layer.Screen.TabBar



@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routs.HomeScreen) {
        composable<Routs.HomeScreen> {
            TabBar(navController)
        }
        composable<Routs.pdfView> {
            val data = it.toRoute<Routs.pdfView>()

            PdfView(pdfUrl = data.pdfUrl)
        }
        composable<Routs.BookByCategory>{
            val data = it.toRoute<Routs.BookByCategory>()
            BookByCategory(navController = navController, category = data.category )
        }
    }
}