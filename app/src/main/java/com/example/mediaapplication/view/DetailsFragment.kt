package com.example.mediaapplication.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mediaapplication.R
import com.example.mediaapplication.base.BaseActivity


class DetailsFragment() : BaseActivity() {

    var imageView: ImageView? = null
    var title : TextView ? = null
    var imageTitle: String? = null
    var imageUrl : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_details)

        imageView = findViewById<ImageView>(R.id.image)
        title = findViewById<TextView>(R.id.title)

        imageTitle = intent.getStringExtra("title")
        imageUrl = intent.getStringExtra("imgUrl")

        (this as AppCompatActivity).supportActionBar!!.title = imageTitle

        setData()
    }

    private fun setData(){
        title?.text = imageTitle
        Glide.with(this).load(imageUrl).into(imageView!!)
    }
}
