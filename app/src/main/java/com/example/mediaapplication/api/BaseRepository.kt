package com.example.mediaapplication.api

import com.example.mediaapplication.utils.Constants
import retrofit2.Response
import java.io.IOException

open class BaseRepository {
    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Results<T> = try {
        val response = call.invoke()
        if (response.isSuccessful && (response.code() == (Constants.STATUS_SUCCESS) || response.code() == (Constants.STATUS_SUCCESS201))) {
            Results.Success(response.body()!!)
        } else if (response.code() == Constants.INTERNAL_ERROR) {
            Results.Error(IOException("Internal Server Error"))
        } else if (response.code() == Constants.TOKEN_EXPIRED) {
            Results.Error(IOException("${response.code()}"))
        } else if (response.errorBody() != null) {
            Results.Error(IOException("Something error..."))

        } else {
            Results.Error(IOException("Something error..."))
        }
    } catch (e: Exception) {
        Results.Error(IOException(errorMessage, e))
    }

    val <T> T.exhaustive: T get() = this
}