package com.gshockv.kmovies.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gshockv.kmovies.R
import com.gshockv.kmovies.data.model.Movie
import com.gshockv.kmovies.data.model.posterImageUrl
import kotlinx.android.synthetic.main.row_movie_view.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private var items = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.row_movie_view, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = items[position]
        holder.bind(movie)
    }

    override fun getItemCount() = items.size

    fun updateData(movies: List<Movie>) {
        this.items = movies.toMutableList()
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) = with(itemView) {
            Glide.with(itemView)
                .load(movie.posterImageUrl)
                .into(imageViewPoster)

            textViewTitle.text = movie.title
            textViewOverview.text = movie.overview
            textViewReleased.text = movie.releaseDate
        }
    }
}
