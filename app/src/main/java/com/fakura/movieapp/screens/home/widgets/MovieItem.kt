package com.fakura.movieapp.screens.home.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.fakura.movieapp.model.Movie
import com.fakura.movieapp.model.getMovies
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick:(String)-> Unit = {}){

    var expanded by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            onItemClick(movie.id)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start){
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp) {
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.images[0])
                    .crossfade(true)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = null,
                )
            }

            Column(modifier = Modifier
                .padding(4.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.caption)
                Text(text = "Year: ${movie.year}",
                    style = MaterialTheme.typography.caption)

                AnimatedVisibility(visible = expanded){
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp,fontWeight = FontWeight.Light)){
                                append(movie.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))
                        Divider()
                        Text(text = "Actors: ${movie.actors}",
                            style = MaterialTheme.typography.caption)
                        Text(text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.caption)
                    }
                }
                Icon(
                    imageVector = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier.size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.Gray
                )
            }
        }
    }
}
