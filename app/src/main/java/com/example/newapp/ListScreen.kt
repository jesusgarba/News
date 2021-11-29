package com.example.newapp

import androidx.compose.material.Scaffold
import androidx.compose.material.Text

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newapp.model.News
import com.example.newapp.ui.theme.NewAppTheme

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListScreenViewModel = hiltViewModel()
) {
    val newsList by viewModel.getNews().observeAsState(initial = emptyList())
    ListScreen(navController, newsList)
}

@Composable
fun ListScreen(
    navController: NavController,
    news: List<News>
) {
    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text(text = "Top news")},
                    )
        }
    )
    {


    }

}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    NewAppTheme {
        ListScreen(
            navController = rememberNavController(),
            news = arrayListOf(
                News(
                    "Title", "Content description", "", "",
                    "https://placeHolder"
                ),
                News(
                    "Title2", "Content description", "", "",
                    "https://placeHolder"
                ),
            )
        )
    }
}