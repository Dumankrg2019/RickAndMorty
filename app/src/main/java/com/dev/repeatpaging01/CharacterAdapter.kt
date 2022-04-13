package com.dev.repeatpaging01

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.repeatpaging01.databinding.ItemCharacterBinding
import com.dev.repeatpaging01.network.response.CharacterData

class CharacterAdapter: PagingDataAdapter<CharacterData,CharacterAdapter.CharacterViewHolder>(DiffUtilCallBack()) {

    inner class CharacterViewHolder(val itemBinding: ItemCharacterBinding):RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: CharacterData) {

            itemBinding.tvName.text = data.name
            itemBinding.tvDesc.text = data.species

            Glide.with(itemView)
                .load(data.image)
                .centerCrop()
                .into(itemBinding.imageView)
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
       val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
           return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {

            return oldItem.name == newItem.name
                    && oldItem.species == newItem.species
        }


    }
}