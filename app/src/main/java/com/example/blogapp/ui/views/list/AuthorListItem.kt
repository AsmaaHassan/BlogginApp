package com.example.blogapp.ui.views

import android.R
import android.view.Gravity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blogapp.models.Author
import com.example.blogapp.ui.theme.*
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun AuthorViewItem(navController: NavController, author: Author,
                   onItemClicked: (item: Author) -> Unit){
    Card( modifier = Modifier
        .fillMaxSize()
        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
    ) {

            AuthorDataItem(author = author, modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)

                .clickable {
                    onItemClicked(author)
                    navController.navigate("authorDetails")
                })
        }
}


@Composable
fun AuthorDataItem(author: Author, modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        author.avatarUrl?.let { AuthorAvatar(imgPath = it) }
        Column(
            modifier = modifier
        ) {
            author.let {
                it.name?.let { it1 ->
                    Text(
                        text = it1,
                        style = MaterialTheme.typography.body1,
                        color = Cyan900,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center

                    )
                }
                it.email?.let { it1 ->
                    Text(
                        text = it1,
                        color = BlueGray800,
                        style = MaterialTheme.typography.body2
                    )
                }

            }
        }
    }

}

@Composable
fun AuthorAvatar(imgPath: String){
    Image(modifier = Modifier
        .padding(end = 5.dp)
        .width(150.dp)
        .height(150.dp),

    painter = rememberCoilPainter(request = imgPath),
        contentDescription = ""
    )

}

