package com.example.e_book.Ui_layer.tabSetUp

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs() {

    val list = listOf(
        "Category" to Icons.Rounded.Category,
        "All Books" to Icons.Rounded.Book
    )

    val pagerState = rememberPagerState(pageCount = { list.size })
    val customCoroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {

        TabRow(selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Black,
            indicator = {
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(it[pagerState.currentPage]),
                    height = 5.dp,
                    color = Color.White
                )
            }
        ) {
            list.fastForEachIndexed { i, pair ->
                Tab(
                    selected = pagerState.currentPage == i,
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Gray,
                    onClick = {
                        customCoroutineScope.launch {
                            pagerState.animateScrollToPage(i)
                        }

                    },
                    icon = {
                        Icon(
                            imageVector = pair.second,
                            contentDescription = null,
                            //tint = if (pagerState.value == i) Color.White else Color.Gray
                        )
                    },
                    text = {
                        Text(
                            text = pair.first,/* color = if(pagerState.value == i) Color.White else Color.Gray*/
                        )
                    }
                )
            }
        }



    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize())  {
        when (it) {
            0 ->Text(text = "Category")
            1 -> Text(text = "All Books")
        }
    }
}

}