package ercanduman.retrofitdemo.data.entities

import com.google.gson.annotations.SerializedName

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    @SerializedName("body")
    val text: String
)