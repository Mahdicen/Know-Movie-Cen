package com.mahdicen.knowmovies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahdicen.knowmovies.data.RawSearch
import com.mahdicen.knowmovies.databinding.ItemSearchBinding

class SearchAdapter(private val data: ArrayList<RawSearch.Search?>? , private val searchMovieEvent: SearchMovieEvent) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    lateinit var binding: ItemSearchBinding

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int) {

            Glide
                .with(binding.root.context)
                .load(data!![position]!!.poster!!)
                .into(binding.imgPosterSearch)

            binding.txtTitleSearch.text = data[position]!!.title
            binding.txtGenre.text = "Tap to see more ->"
            binding.txtRateSearch.text = "⭐ Tap to see"
            binding.txtTypeSearch.text = data[position]!!.type
            binding.txtYear.text = data[position]!!.year

            itemView.setOnClickListener {
                searchMovieEvent.clickOnMovie(data[adapterPosition]!!.title!! , data[adapterPosition]!!.year!!.toInt())
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding = ItemSearchBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = data!!.size

    interface SearchMovieEvent {
        fun clickOnMovie(title :String , year: Int)
    }

}