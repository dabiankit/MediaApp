package com.example.mediaapplication.viewmodel

import android.content.Context
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mediaapplication.api.GenericErrorHandler
import com.example.mediaapplication.api.RetrofitInstance
import com.example.mediaapplication.model.AuthorModelClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private var _catagoryDetailsList: MutableLiveData<AuthorModelClass>? = MutableLiveData()
    var catagoryDetails: LiveData<AuthorModelClass> = _catagoryDetailsList!!


    fun loadAllNewsList(context: Context?) {

        RetrofitInstance.apiInterface?.getAllAuthorData()
            ?.enqueue(object : Callback<AuthorModelClass> {
                override fun onResponse(
                    call: Call<AuthorModelClass>,
                    response: Response<AuthorModelClass>
                ) {
                    if (response.body() != null) {
                        _catagoryDetailsList?.postValue(response.body())
                    } else {
                        val errorBean: AuthorModelClass = GenericErrorHandler().handlerError(response)!!
                        _catagoryDetailsList?.postValue(errorBean)
                    }
                }

                override fun onFailure(
                    @NonNull call: Call<AuthorModelClass>?,
                    t: Throwable
                ) {
                    t.printStackTrace()
                }
            })
    }
}