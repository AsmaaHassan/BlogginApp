package com.example.blogapp.data.remote

import android.util.Log
import com.example.blogapp.models.Author
import com.example.blogapp.models.Post
import retrofit2.Retrofit

class RemoteDataSource(retrofit: Retrofit)  {
    private val serviceApi: ServiceApi = retrofit.create(ServiceApi::class.java)

    sealed class ResultAuthors {
        data class Success(val authorList: List<Author>?) :ResultAuthors()
        data class Failure(val throwable: Throwable): ResultAuthors()
    }

    sealed class ResultAuthorPosts {
        data class Success(val postList: List<Post>?) :ResultAuthorPosts()
        data class Failure(val throwable: Throwable): ResultAuthorPosts()
    }

    suspend fun getAuthors(page: Int):ResultAuthors{
        return try {
            val authorList = serviceApi.fetchAuthorsApi()
            Log.d("getAuthors","success "+authorList)
            ResultAuthors.Success(authorList = authorList.body())
        }catch (exception:Exception){
            Log.d("getAuthors","failure "+exception)
            ResultAuthors.Failure(exception)
        }
    }


    suspend fun getAuthorPosts(id: Int):ResultAuthorPosts{
        return try {
            val postList = serviceApi.fetchAuthorPostsApi(id)
            Log.d("getAuthorPosts","success "+postList)
            ResultAuthorPosts.Success(postList = postList.body())
        }catch (exception:Exception){
            Log.d("getAuthorPosts","failure "+exception)
            ResultAuthorPosts.Failure(exception)
        }
    }
}