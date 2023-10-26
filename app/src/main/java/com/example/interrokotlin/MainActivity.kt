package com.example.interrokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiService.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    Log.d("tag",response.body().toString())
                    // Process the data received from the server
                } else {
                    // Handle the unsuccessful response (3xx, 4xx, 5xx)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                // Handle the HTTP failure
            }
        })
    }

    interface ApiService {
        @GET("posts")
        fun getPosts(): Call<List<Post>>
    }

    object RetrofitClient {
        private const val BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=germinal"

        val instance: ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(ApiService::class.java)
        }
    }
    val apiService = RetrofitClient.instance


}




