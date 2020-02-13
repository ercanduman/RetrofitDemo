package ercanduman.retrofitdemo.data.entities

import com.google.gson.annotations.SerializedName

data class Comment(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    @SerializedName("body")
    val content: String
)