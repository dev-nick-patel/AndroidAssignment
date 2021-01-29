package com.techand.androidassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.techand.androidassignment.R
import com.techand.androidassignment.data.local.entities.Row
import com.techand.androidassignment.databinding.RawItemViewBinding


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

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    class MainViewHolder(private val itemBinding: RawItemViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        private lateinit var item: Row

        fun bind(item: Row) {
            this.item = item
            itemBinding.tvTitle.text = item.title
            itemBinding.tvDescription.text = item.description
            itemBinding.imageView.loadImage(item.imageHref, progressBar())
        }

        private fun progressBar(): CircularProgressDrawable {
            val circularProgressDrawable = CircularProgressDrawable(itemBinding.root.context)
            circularProgressDrawable.strokeWidth = 10f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            return circularProgressDrawable
        }
    }

    fun setItems(items: List<Row>) {
        this.items.clear()
        for (item in items) {
            if (!item.noData()) {
                this.items.add(item)
            }
        }
        notifyDataSetChanged()
    }
}

private fun ImageView.loadImage(imageUrl: String?, progressBar: CircularProgressDrawable) {
    val imgUrl = validUrl(imageUrl)
    Glide.with(this)
        .load(imgUrl)
        .apply(
            RequestOptions.placeholderOf(progressBar)
                .error(R.drawable.no_image)
                .priority(Priority.HIGH)
        )
        .into(this)

}
//with https only image will be loaded in device
fun validUrl(imageUrl: String?): String? {
    return imageUrl?.replace("http:","https:")
}




