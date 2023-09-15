package com.mahdicen.knowmovies.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahdicen.knowmovies.data.RawMovie
import com.mahdicen.knowmovies.databinding.ItemPosterBinding
import com.mahdicen.knowmovies.local.Movie
import java.time.Year

class HomeAdapter(private val data: ArrayList<Movie> , private val movieEvents: MovieEvents) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    lateinit var binding: ItemPosterBinding

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int) {

            Glide
                .with(binding.root)
                .load(data[position].poster)
                .into(binding.imgPosterMain)

            binding.txtTitleMain.text = data[position].title
            binding.txtType.text = data[position].type
            binding.txtYearMain.text = data[position].year.toString()

            itemView.setOnClickListener {
                movieEvents.clickOnMovie(data[adapterPosition].title , data[adapterPosition].year)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = ItemPosterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(newData: ArrayList<Movie>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    interface MovieEvents {
        fun clickOnMovie(title :String , year: Int)
    }

}