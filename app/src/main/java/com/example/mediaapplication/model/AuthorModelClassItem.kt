package com.example.mediaapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorModelClassItem(
    val author: String,
    val author_url: String,
    val filename: String,
    val format: String,
    val height: Int,
    val id: Int,
    val post_url: String,
    val width: Int
): Parcelable