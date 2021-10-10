package com.example.blogapp.ui.views.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blogapp.models.Author
import com.example.blogapp.models.Post
import com.example.blogapp.ui.theme.BlueGray800
import com.example.blogapp.ui.theme.Cyan800
import com.example.blogapp.ui.views.AuthorDataItem
import com.example.blogapp.ui.views.AuthorViewItem
import com.google.accompanist.coil.rememberCoilPainter


@Composable
fun PostItem(post: Post) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
    ) {
        Column() {
            post.imageUrl?.let { PostImage(imgPath = it) }

            PostDataItem(
                post = post, modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp)
            )
        }
    }
}

@Composable
fun PostDataItem(post: Post, modifier: Modifier) {

    Column(
        modifier = modifier
    ) {
        post.let {
            it.title?.let { it1 ->
                Text(
                    text = it1,
                    style = MaterialTheme.typography.h5,
                    color = Cyan800,
                    textAlign = TextAlign.Center

                )
            }
            it.body?.let { it1 ->
                Text(
                    text = it1,
                    style = MaterialTheme.typography.body1,
                    color = BlueGray800,
                    modifier = Modifier.padding(bottom = 7.dp, top = 7.dp)
                )
            }
        }
    }

}

@Composable
fun PostImage(imgPath: String) {
    Image(
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),

        painter = rememberCoilPainter(request = imgPath),
        contentDescription = ""
    )

}