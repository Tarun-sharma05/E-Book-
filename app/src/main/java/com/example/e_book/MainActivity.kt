package com.example.e_book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.e_book.Ui_layer.Navigation.AppNavigation
import com.example.e_book.Ui_layer.Screen.TabBar
import com.example.e_book.ui.theme.EBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            EBookTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                        AppNavigation()

                }
            }
        }
    }
}



