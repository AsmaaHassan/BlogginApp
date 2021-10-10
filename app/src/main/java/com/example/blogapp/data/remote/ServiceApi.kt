package com.example.blogapp.data.remote

import com.example.blogapp.models.Author
import com.example.blogapp.models.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("/authors")
    suspend fun fetchAuthorsApi() : Response<List<Author>>

    @GET("/posts")
    suspend fun fetchAuthorPostsApi(@Query("authorId") authorId: Int) : Response<List<Post>>

}