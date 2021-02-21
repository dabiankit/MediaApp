package com.example.mediaapplication.api

import com.example.mediaapplication.utils.Constants
import com.example.mediaapplication.model.AuthorModelClass
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET(Constants.BASE_URL)
    fun getAllAuthorData(): Call<AuthorModelClass>

}