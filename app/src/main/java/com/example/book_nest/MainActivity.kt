package com.example.book_nest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.book_nest.Ui_layer.Navigation.AppNavigation
import com.example.book_nest.ui.theme.BookNestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookNestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                        AppNavigation()

                }
            }
        }
    }
}



