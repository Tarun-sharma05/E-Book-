package com.example.e_book.Ui_layer.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun TabBar( navController: NavController){

    val tabs= listOf(
        TabItem("Category", Icons.Rounded.Category),
        TabItem("All Books", Icons.Rounded.Book),
    )

    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val customCoroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {

        TabRow(selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Black,
            indicator = {
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(it[pagerState.currentPage]),
                    height = 5.dp,
                    color = Color.White
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = pagerState.currentPage == index,
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Gray,
                    onClick = {
                        customCoroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }

                    },
                    icon = {
                        Icon(imageVector = tab.icon, contentDescription = null)
                    },
                    text = {
                        Text(text = tab.name)
                    }
                )
            }
        }



        HorizontalPager(state = pagerState)  {
            when (it) {
                0 -> Category(navController = navController )
                1 -> AllBooksScreen(navController = navController)
            }
        }
    }
}


data class TabItem(
    val name: String,
    val icon: ImageVector,

)