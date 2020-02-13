package ercanduman.retrofitdemo.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ercanduman.retrofitdemo.CONTENT_BASE_URL
import ercanduman.retrofitdemo.R
import ercanduman.retrofitdemo.data.entities.Post
import ercanduman.retrofitdemo.data.network.JsonPlaceHolderApi
import ercanduman.retrofitdemo.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        retrofit = Retrofit.Builder()
            .baseUrl(CONTENT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Get content from url?", Snackbar.LENGTH_LONG)
                .setAction(android.R.string.yes) { getContentFromUrl() }.show()
        }
    }

    private fun getContentFromUrl() {
        activity_main_progressbar.show()
        logd("getContentFromUrl() - called.")

        // create api instance
        val api: JsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        // call retrofit for network operation
        val postListCall = api.getPosts()

        /*
         To make call in background thread enqueue() function should be called, instead of execute().
         (Call.execute() runs on main thread)
         */
        postListCall.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t.message?.let { logd(it) }
                printException(t as Exception)
                activity_main_progressbar.hide()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    toast("Network call is successful.")
                    val posts = response.body()
                    if (!posts.isNullOrEmpty()) {
                        val stringBuilder = StringBuilder()
                        posts.forEach { post ->
                            stringBuilder.append(post.toString()).append("\n").append("\n")
                        }
                        activity_main_content.text = stringBuilder.toString()
                    } else {
                        activity_main_content.text = getString(R.string.no_data_found)
                    }
                } else {
                    toast("Network call failed! Code: ${response.code()}")
                    logd("Response from network call is no successful! Code: ${response.code()}")
                }
                activity_main_progressbar.hide()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
