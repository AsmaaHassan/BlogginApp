package com.example.blogapp.data.remote

data class APIException(
    override var message: String?,
    @ErrorType
    var errorType: Int
) : RuntimeException()