package com.example.newapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newapp.ui.theme.NewAppTheme
import dagger.hilt.android.AndroidEntryPoint

object  Destinations {
    const val LIST_SCREEN = "LIST_SCREEN"
    const val DETAILS_SCREEN = "DETAILS_SCREEN"

}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                      navController = navController,
                        startDestination = Destinations.LIST_SCREEN
                    ){
                        composable(Destinations.LIST_SCREEN){
                            ListScreen(navController = navController)
                        }
                        composable("${Destinations.DETAILS_SCREEN}/{newTitle}"){
                            //TODO: Navigation to details
                        }

                    }

                }
            }
        }
    }
}
