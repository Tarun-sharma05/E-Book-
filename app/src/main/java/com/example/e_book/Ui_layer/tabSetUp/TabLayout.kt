package com.example.e_book.Ui_layer.tabSetUp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout(modifier: Modifier = Modifier) {
    val pagerState  = remember{
        mutableStateOf(0)
    }
    Tabs()
//    TabContent()
}