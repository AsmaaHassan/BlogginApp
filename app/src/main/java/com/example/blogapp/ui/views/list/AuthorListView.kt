package com.example.blogapp.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.blogapp.R
import com.example.blogapp.models.Author
import com.example.blogapp.ui.theme.BlogAppTheme
import com.example.blogapp.viewmodels.AuthorViewModel

@ExperimentalFoundationApi
@Composable
fun AuthorListView(
    authorList: List<Author>,
    navController: NavController,
    onItemClicked: (item: Author) -> Unit
) {

    var listState = rememberLazyListState()

    LazyColumn(state = listState) {
//        stickyHeader {
//            MainHeader()
//        }
        itemsIndexed(authorList) { index, item ->
            AuthorViewItem(navController = navController, author = item, onItemClicked)
        }
    }

}

//@Composable
//fun MainHeader() {
//    Surface(
//        Modifier
//
//            .background(MaterialTheme.colors.background)
//    ) {
//        Image(painterResource(R.drawable.main_icon), contentDescription = "",
//            modifier = Modifier.size(50.dp)
//        .height(50.dp)
//                .padding(start=3.dp, top=5.dp, bottom=5.dp))
//    }
//
//}

@ExperimentalFoundationApi
@Composable
fun MainList(
    navController: NavController,
    authorViewModel: AuthorViewModel,

    ) {
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }

    AuthorListView(
        authorList = authorViewModel.authors,
        navController,
        onItemClicked = authorViewModel::itemClicked
    )
    if (snackbarVisibleState) {
        Snackbar(
            modifier = Modifier.padding(8.dp)
        ) { authorViewModel.errorLiveData.message?.let { Text(text = it) } }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val author = Author(
        name = "Asmaa Hassan",
        email = "asmaa.h_elmoghazy@yahoo.com",
        avatarUrl = "https://upload.wikimedia.org/wikipedia/ar/9/99/Ava_poster.jpeg"
    )

    BlogAppTheme {

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                author.avatarUrl?.let { AuthorAvatar(imgPath = it) }
                AuthorDataItem(
                    author = author, modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                )
            }
        }
    }
}
