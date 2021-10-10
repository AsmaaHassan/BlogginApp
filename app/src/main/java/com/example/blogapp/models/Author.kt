package com.example.blogapp.models

import com.google.gson.annotations.SerializedName

data class Address(

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)

data class Author(

	@field:SerializedName("address")
	val address: Address? = null,

	@field:SerializedName("avatarUrl")
	val avatarUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("userName")
	val userName: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
