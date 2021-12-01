package com.example.newapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.newapp.model.News
import com.example.newapp.ui.theme.NewAppTheme

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListScreenViewModel = hiltViewModel()
) {
    val newsList by viewModel.getNews().observeAsState(initial = emptyList())
    ListScreen2(navController, newsList)
}

@Composable
fun ListScreen2(
    navController: NavController,
    news: List<News>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Top news") },
            )
        }
    )
    {
        LazyColumn {
            items(news) { new ->
                Card(shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            //TODO: Navegar a detalles
                        }
                ) {
                    Column() {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f),
                            painter = rememberImagePainter(
                                data = new.urlToImage,
                            builder = {
                                placeholder(R.drawable.placeholder)
                                error(R.drawable.placeholder)
                            }
                                ),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                        Column(Modifier.padding(8.dp)) {
                            Text(text = new.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(text = new.content ?: "", maxLines = 3)
                        }
                    }

                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    NewAppTheme {
        ListScreen2(
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
                News(
                    "Title3", "Content description", "", "",
                    "https://placeHolder"
                ),
                News(
                    "Title4", "Content description", "", "",
                    "https://placeHolder"
                ),
            )
        )
    }
}