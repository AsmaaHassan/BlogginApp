package com.example.blogapp.data

import android.content.Context
import android.util.Log
import com.example.blogapp.data.remote.RemoteDataSource

import com.example.blogapp.utils.ConnectivityUtil

class BloggingRepo(
    private val remoteDataSource: RemoteDataSource,
    private val context: Context
) {

    suspend  fun getAuthorsFromServer(page: Int) {
        remoteDataSource.getAuthors(page)
    }

     suspend fun getAuthors(page: Int): RemoteDataSource.ResultAuthors {
        val isConnected = ConnectivityUtil.isInternetAvailable(context = context)
        return remoteDataSource.getAuthors(page = page)
//         else
             //* call local one

    }

    suspend fun getAuthorPosts(authorId: Int): RemoteDataSource.ResultAuthorPosts {
        val isConnected = ConnectivityUtil.isInternetAvailable(context = context)
        return remoteDataSource.getAuthorPosts(id = authorId)
//         else
        //* call local one

    }
}