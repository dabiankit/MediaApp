package com.example.mediaapplication.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mediaapplication.utils.Constants
import com.example.mediaapplication.R
import com.example.mediaapplication.model.AuthorModelClassItem
import kotlinx.android.synthetic.main.sample_item.view.*

class MainAdapter(
    private val listener: OnItemCLickListener,
    private val context: Context?
) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var subCategoryResponse: ArrayList<AuthorModelClassItem>? = null

    fun setList(subCategoryResponse: ArrayList<AuthorModelClassItem>) {
        this.subCategoryResponse = subCategoryResponse
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sample_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCategoryTitle.text = subCategoryResponse!![position].author
        Glide.with(context!!).load(Constants.IMG_URL + subCategoryResponse!![position].id).into(holder.ivImage)
        holder.rlParent.setOnClickListener {
            listener.onClick(subCategoryResponse!![position].author,Constants.IMG_URL + subCategoryResponse!![position].id)
        }

    }

    override fun getItemCount(): Int {
        if (subCategoryResponse == null)
            return 0
        else
            return subCategoryResponse!!.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage = view.ivSubCategory!!
        val tvCategoryTitle = view.title!!
        val rlParent = view.rlParent!!
    }

    interface OnItemCLickListener {
        fun onClick(title: String?, imgUrl: String?)
        //fun onClickTitle(title: String)
    }
}