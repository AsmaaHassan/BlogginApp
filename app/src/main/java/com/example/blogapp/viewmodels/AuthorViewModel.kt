package com.example.blogapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.blogapp.BlogginApp
//import com.example.blogapp.BlogginApp
import com.example.blogapp.data.BloggingRepo
import com.example.blogapp.data.remote.APIException
import com.example.blogapp.data.remote.ErrorType
import com.example.blogapp.data.remote.RemoteDataSource
import com.example.blogapp.models.Author
import com.example.blogapp.models.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
//import okhttp3.internal.Internal.instance
//import org.kodein.di.KodeinAware
import org.kodein.di.*

class AuthorViewModel(ctx: Context) : ViewModel(), DIAware {
    override val di by lazy { (ctx as BlogginApp).di }
    private val bloggingRepo: BloggingRepo by instance()

    var authors: List<Author> by mutableStateOf(listOf())
    var posts: List<Post> by mutableStateOf(listOf())
    val err = APIException(errorType = ErrorType.UNEXPECTED, message = "Error")
    var errorLiveData: APIException by mutableStateOf(value = err)

    lateinit var clickedItem: Author

    init {
        getAuthors(1)
    }

    fun getAuthors(page: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = bloggingRepo.getAuthors(page)
                when (response) {
                    is RemoteDataSource.ResultAuthors.Success -> {

                        Log.d("MainViewModel", "Success")
                        authors = response.authorList as List<Author>
                    }
                    is RemoteDataSource.ResultAuthors.Failure -> {
                        Log.d("MainViewModel", "FAILURE")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorLiveData =
                        APIException("No internet connection", ErrorType.NETWORK)
                }
            }
        }
    }

    fun getAuthorposts(authorId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = bloggingRepo.getAuthorPosts(authorId = authorId)
                when (response) {
                    is RemoteDataSource.ResultAuthorPosts.Success -> {

                        Log.d("MainViewModel", "getAuthorposts Success")
                        posts = response.postList as List<Post>
                    }
                    is RemoteDataSource.ResultAuthorPosts.Failure -> {
                        Log.d("MainViewModel", "getAuthorposts FAILURE")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorLiveData =
                        APIException("No internet connection", ErrorType.NETWORK)
                }
            }
        }
    }

    fun itemClicked(item: Author) {
        clickedItem = item
        posts = emptyList()
        item.id?.let { getAuthorposts(it) }
    }
}


