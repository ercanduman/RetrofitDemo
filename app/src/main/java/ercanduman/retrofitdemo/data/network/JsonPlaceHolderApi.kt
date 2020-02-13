package ercanduman.retrofitdemo.data.network

import ercanduman.retrofitdemo.data.entities.Post
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @GET("posts")
    fun getPosts(): Call<List<Post>>
}