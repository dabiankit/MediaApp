package com.example.mediaapplication.api

import com.example.mediaapplication.model.AuthorModelClass
import com.google.gson.Gson
import retrofit2.Response
import java.io.IOException

class GenericErrorHandler {
    fun handlerError(response: Response<*>): AuthorModelClass? {
        var responseMessage: String? = null
        return try {
            responseMessage = response.errorBody()!!.string()
            val gson = Gson()
            gson.fromJson(responseMessage, AuthorModelClass::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}