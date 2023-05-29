package com.example.assignment3_webtoon

import android.transition.TransitionManager
import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment3_webtoon.databinding.RowBinding

class DayAdapter(val items:ArrayList<WebtoonData>)
    : RecyclerView.Adapter<DayAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root){
        init{
            binding.tuple.setOnClickListener {
                val title = binding.title.text.toString()
                for (i in items.indices) {
                    if (items[i].Title == title) {
                        if(items[i].Open == true){
                            items[i].Open = false
                        }else{
                            items[i].Open = true
                        }
                    }
                }
                if(binding.explanation.visibility == View.GONE){
                    binding.explanation.visibility = View.VISIBLE
                }else{
                    binding.explanation.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.text = items[position].Title
        Glide.with(holder.itemView.context)
            .load(items[position].Imgurl)
            .into(holder.binding.thumbnail)
        holder.binding.explanation.text = items[position].Extra
        if(items[position].Open) {
            holder.binding.explanation.visibility = View.VISIBLE
        }else{
            holder.binding.explanation.visibility = View.GONE
        }
    }
}