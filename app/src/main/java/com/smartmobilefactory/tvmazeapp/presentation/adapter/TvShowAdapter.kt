package com.smartmobilefactory.tvmazeapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartmobilefactory.tvmazeapp.databinding.TvShowItemBinding
import com.smartmobilefactory.tvmazeapp.domain.model.TvShowResponseItem
import com.smartmobilefactory.tvmazeapp.presentation.utils.loadImage

class TvShowAdapter(var tvShowList: ArrayList<TvShowResponseItem>) :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    var onItemClick: ((TvShowResponseItem) -> Unit)? = null

    fun updateTvShows(newShowList: ArrayList<TvShowResponseItem>) {
        this.tvShowList.clear()
        tvShowList.addAll(newShowList)
        notifyDataSetChanged()
    }


    class TvShowViewHolder(private val itemBinding: TvShowItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(tvShowItem: TvShowResponseItem) {
            tvShowItem.show.image?.let {  image ->
                itemBinding.tvShowImageView.loadImage(tvShowItem.show.image.medium)
            }
            itemBinding.tvShowNameTv.text = tvShowItem.show.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemBinding =
            TvShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemBinding)
    }


    override fun getItemCount(): Int {
        return tvShowList.size
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(tvShowList[position])

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(tvShowList[position])

        }
    }


}