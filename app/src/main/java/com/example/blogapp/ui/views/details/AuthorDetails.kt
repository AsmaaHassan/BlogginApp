package com.example.blogapp.ui.views.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blogapp.models.Author
import com.example.blogapp.models.Post
import com.example.blogapp.ui.theme.BlueGray800
import com.example.blogapp.ui.theme.Cyan900
import com.example.blogapp.ui.views.AuthorAvatar
import com.example.blogapp.ui.views.AuthorDataItem
//import com.example.blogapp.ui.views.MainHeader
import com.example.blogapp.viewmodels.AuthorViewModel


@ExperimentalFoundationApi
@Composable
fun AuthorDetails(author: Author, posts: List<Post>) {

    var listState = rememberLazyListState()
    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)

    ) {
        DetailsHeader(author)
        LazyColumn(state = listState) {
            itemsIndexed(posts) { index, item ->
                PostItem(post = item)
            }
        }

    }
}

@Composable
fun DetailsHeader(author: Author) {
    Card(
        modifier = Modifier
//        .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
    ) {
        AuthorDataItem(
            author = author, modifier = Modifier
//        .fillMaxSize()
//                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 20.dp)
        )
    }

}

@ExperimentalFoundationApi
@Composable
fun AuthorDetails(authorViewModel: AuthorViewModel) {
    AuthorDetails(
        posts = authorViewModel.posts,
        author = authorViewModel.clickedItem
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun DefaultPreview() {
    val post: Post = Post(title = "title", imageUrl = "")
    val author: Author = Author(name = "Asmaa Hassan", email = "asmaa.h_elmoghazy@yahoo.com")
    AuthorDetails(author = author, posts = listOf(post))
}
