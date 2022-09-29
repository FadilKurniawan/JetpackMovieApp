package com.fakura.movieapp.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fakura.movieapp.model.Movie
import com.fakura.movieapp.model.getMovies
import com.fakura.movieapp.navigation.MovieScreens
import com.fakura.movieapp.screens.home.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController){
    Scaffold (topBar = {
        TopAppBar (
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier= Modifier.fillMaxWidth()
        ){
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Movies",Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,)
                }
        }
    }){
        Surface(color = MaterialTheme.colors.background) {
            MainContent(navController = navController)
        }
    }
}

@Composable
fun MainContent(
    movieList: List<Movie> = getMovies() , navController: NavController
){
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = movieList){
                MovieRow(movie = it) { movie->
                    navController.navigate(
                        route = MovieScreens.DetailsScreen.name+"/$movie"
                    )
                }
            }
        }
    }
}
