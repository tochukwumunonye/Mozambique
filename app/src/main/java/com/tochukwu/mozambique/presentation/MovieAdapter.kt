package com.tochukwu.mozambique.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tochukwu.mozambique.databinding.ItemMoviesBinding
import com.tochukwu.mozambique.data.remote.dto.Search
import com.tochukwu.mozambique.domain.model.Movie
import javax.inject.Inject

class MovieAdapter @Inject constructor(): androidx.recyclerview.widget.ListAdapter<Movie, MovieAdapter.MovieViewHolder>(
    DIFF_COMPARATOR){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }



    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)

        if(currentItem != null){
            holder.bind(currentItem)
        }
    }

    inner class MovieViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(app: Movie) {
            binding.apply {

                movieYear.text = app.year
                movieTitle.text = app.title
                Glide.with(itemView).load(app.poster).into(poster)
                setOnClickListener(app)
            }
        }

        private fun setOnClickListener(app: Movie) {
            binding.itemMoviesLayout.setOnClickListener { view ->
                navigateToDetail(app, view)
            }
        }

        private fun navigateToDetail(app: Movie, view: View) {
            val directions = MovieFragmentDirections.actionMovieFragmentToDetailFragment(app)

            view.findNavController().navigate(directions)
        }
    }
        companion object {
        private val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem.year == newItem.year
            }
        }
    }

}



/**



inner class PosterViewHolder(private val binding: CharactersRowBinding) : RecyclerView.ViewHolder(binding.root){
fun bind(app: PosterDtoItem){
binding.apply{

paymentName.text = app.name
Glide.with(itemView).load(app.poster).into(logo)

setOnClickListener(app)

}
}

private fun setOnClickListener(app: PosterDtoItem){
binding.detailItem.setOnClickListener { view ->
navigateToDetail(app, view)
}
}

private fun navigateToDetail(app: PosterDtoItem, view: View) {
val directions = CharacterFragmentDirections.actionCharacterFragmentToDisneyDetail(app)
view.findNavController().navigate(directions)
}


}



**/