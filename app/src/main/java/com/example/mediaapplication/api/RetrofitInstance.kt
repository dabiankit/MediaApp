package com.example.mediaapplication.api

import com.example.mediaapplication.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance : Interceptor {
    private var mInstance: RetrofitInstance? = null

    // <-- this is the important line!
    var apiInterface: APIInterface? = null
        get() {
            if (field == null) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val httpClient = OkHttpClient.Builder()
                httpClient.connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES) // write timeout
                    .readTimeout(2, TimeUnit.MINUTES)
                    .addInterceptor(logging) // <-- this is the important line!
                field = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(httpClient.build())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIInterface::class.java)

                //  val apiService: ApiService = getRetrofit().create(ApiService::class.java)

            }
            return field
        }
    //    private set

    val instance: RetrofitInstance?
        get() {
            if (mInstance == null) {
                mInstance = RetrofitInstance
            }
            return mInstance
        }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        val response = chain.proceed(requestBuilder.build())

        // Throw specific Exception on HTTP 204 response code

        // Throw specific Exception on HTTP 204 response code
        if (response.code === 204) {
            /*throw ("There is no content")*/
        }
        return response
    }

}