package com.fakura.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.fakura.movieapp.model.Movie
import com.fakura.movieapp.model.getMovies
import com.fakura.movieapp.screens.home.widgets.MovieRow

@Composable
fun DetailsScreen (navController: NavController, movieId: String?){

    val newMovieList = getMovies().filter { movie -> movie.id == movieId }

    Scaffold( topBar = {
        TopAppBar (
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
                ){
            Row(horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                })
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = "Movie Detail")
            }
        }
    }) {
    Surface (modifier = Modifier.fillMaxHeight().fillMaxWidth()){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            MovieRow(movie = newMovieList.first())
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(text = newMovieList[0].title, style = MaterialTheme.typography.h5)
            HorizontalScrollableImageView(newMovieList)
        }
    }

    }
}

private fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) { image ->
            Card(modifier = Modifier.padding(12.dp).size(240.dp), elevation = 5.dp) {
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "Movie Poster"
                )
            }
        }
    }
}