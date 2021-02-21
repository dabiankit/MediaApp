package com.example.mediaapplication.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mediaapplication.R
import com.example.mediaapplication.base.BaseActivity
import com.example.mediaapplication.model.AuthorModelClass
import com.example.mediaapplication.model.AuthorModelClassItem
import com.example.mediaapplication.viewmodel.MainViewModel


class MainActivity : BaseActivity() {

    var recyclerView: RecyclerView? = null
    var mainAdapter: MainAdapter? = null
    private lateinit var gridLayoutManager: GridLayoutManager
    lateinit var mainViewModel: MainViewModel
    var dataList: ArrayList<AuthorModelClassItem>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        recyclerView = findViewById(R.id.rvCategories)

        mainViewModel.loadAllNewsList(this)

        setAdapter()
        setObserver()
    }

    private fun setAdapter(){
        gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL // set Horizontal Orientation
        recyclerView?.layoutManager = gridLayoutManager

        if (this != null) {
            mainAdapter =
                MainAdapter(object : MainAdapter.OnItemCLickListener {
                    override fun onClick(title: String?, imgUrl: String?) {
                        Toast.makeText(this@MainActivity,title,Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@MainActivity, DetailsFragment::class.java)
                        intent.putExtra("title",title)
                        intent.putExtra("imgUrl",imgUrl)
                        startActivity(intent)

                       /* this@MainActivity.supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, DetailsFragment(title,imgUrl))
                            .commit()*/
                    }
                }, this)
        }
        recyclerView?.adapter = mainAdapter

    }

    private fun setObserver(){
        this?.let {
            mainViewModel.catagoryDetails?.observe(this){authorModelClass ->
                var authorModelClass: AuthorModelClass = authorModelClass
                dataList?.addAll(authorModelClass!!)
                recyclerView?.adapter = mainAdapter
                mainAdapter?.setList(dataList!!)
            }
        }
    }
}
