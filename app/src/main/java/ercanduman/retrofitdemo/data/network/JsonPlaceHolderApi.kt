package ercanduman.retrofitdemo.data.network

import ercanduman.retrofitdemo.data.entities.Comment
import ercanduman.retrofitdemo.data.entities.Post
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    /**
     * Main url looks like:
     * -> https://jsonplaceholder.typicode.com/posts
     */
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    /**
     *  Main url looks like:
     *  -> https://jsonplaceholder.typicode.com/posts/1/comments
     */
    @GET("posts/1/comments")
    fun getComments(): Call<List<Comment>>
}