package ercanduman.retrofitdemo.data.network

import ercanduman.retrofitdemo.data.entities.Comment
import ercanduman.retrofitdemo.data.entities.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

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

    /**
     *  Main url looks like:
     *  -> https://jsonplaceholder.typicode.com/posts/[1,2,3,...]/comments
     */
    @GET("posts/{id}/comments")
    fun getCommentsForPost(@Path("id") postId: Int): Call<List<Comment>>
}