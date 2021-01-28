package com.techand.androidassignment.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.techand.androidassignment.R
import com.techand.androidassignment.data.local.entities.Row
import com.techand.androidassignment.data.local.entities.RowResult
import com.techand.androidassignment.databinding.RawItemViewBinding
import com.techand.androidassignment.util.Resource

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private val items = ArrayList<Row>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding: RawItemViewBinding =
            RawItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) =
        holder.bind(items[position])


    override fun getItemCount(): Int {
        return items.size
    }

    class MainViewHolder(private val itemBinding: RawItemViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        private lateinit var item: Row

        @SuppressLint("SetTextI18n")
        fun bind(item: Row) {
            this.item = item

            itemBinding.tvTitle.text = item.title
            itemBinding.tvDescription.text = item.description
            if (item.imageHref.isNullOrEmpty())
                itemBinding.imgLin.visibility = View.GONE
            else {
                itemBinding.imgLin.visibility = View.VISIBLE
                Glide.with(itemBinding.root)
                    .load(item.imageHref)
                    .apply(
                        RequestOptions
                            .errorOf(R.drawable.ic_launcher_background)
                            .priority(Priority.HIGH)
                    )
                    .into(itemBinding.imageView)
            }
        }
    }

    fun setItems(items: Resource<RowResult>) {
        this.items.clear()
        for (item in items.data?.rows!!) {
            if (!item.noData())
                this.items.add(item)
        }
        notifyDataSetChanged()
    }
}