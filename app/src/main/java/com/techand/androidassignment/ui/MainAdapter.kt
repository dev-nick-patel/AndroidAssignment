package com.techand.androidassignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techand.androidassignment.R
import com.techand.androidassignment.model.Row
import kotlinx.android.synthetic.main.raw_item_view.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Row>(){
        override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem.imageHref == newItem.imageHref
        }
        override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.raw_item_view,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(item.imageHref).into(imageView)
            tv_title.text = item.title
            tv_description.text = item.description
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}