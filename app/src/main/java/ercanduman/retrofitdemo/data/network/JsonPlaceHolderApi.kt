package ercanduman.retrofitdemo.data.network

import ercanduman.retrofitdemo.data.entities.Comment
import ercanduman.retrofitdemo.data.entities.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceHolderApi {

    /**
     * Main url looks like:
     * -> https://jsonplaceholder.typicode.com/posts
     */
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    /**
     *  Main url looks like:
     *  -> https://jsonplaceholder.typicode.com/posts?userId=[1,2,3, etc.]
     */
    @GET("posts")
    fun getPostsForUser(@Query("userId") userId: Int): Call<List<Post>>

    /**
     * Get post for a user with additional parameters such as "order" and "sort"
     *  Main url looks like:
     *  -> https://jsonplaceholder.typicode.com/posts?userId=4&_sort=id&_order=desc
     *
     *  If sort and order parameters set to null then these parameters will be ignored
     *  If userId set to null, then all users' content will be loaded
     */
    @GET("posts")
    fun getPostsForUserAndSort(
        @Query("userId") userId: Int?,
        @Query("_sort") sort: String?,
        @Query("_order") order: String?
    ): Call<List<Post>>

    /**
     *  Main url looks like:
     *  -> https://jsonplaceholder.typicode.com/posts/1/comments
     */
    @GET("posts/1/comments")
    fun getComments(): Call<List<Comment>>

    /**
     *  Main url looks like:
     *  -> https://jsonplaceholder.typicode.com/posts/[1,2,3, etc.]/comments
     */
    @GET("posts/{id}/comments")
    fun getCommentsForPost(@Path("id") postId: Int): Call<List<Comment>>
}