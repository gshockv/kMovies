package com.gshockv.kmovies.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gshockv.kmovies.BuildConfig
import com.gshockv.kmovies.R
import com.gshockv.kmovies.data.model.MoviesList
import kotlinx.android.synthetic.main.fragment_movies_list.*

class MoviesListFragment : Fragment() {
    private lateinit var viewModel : MoviesListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this).get(MoviesListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val apiKey = BuildConfig.API_KEY
//        Toast.makeText(context, "API_KEY: $apiKey", Toast.LENGTH_SHORT).show()

        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                is MoviesUiState.LoadingState -> showLoadingState()
                is MoviesUiState.ErrorState -> showErrorState()
                is MoviesUiState.DataState -> showDataState(it.data)
            }
        })

        viewModel.loadMoviesList()
    }

    private fun showDataState(data: MoviesList) {
        textViewStateIndicator.text = "Data Received"

        Toast.makeText(context, "Loaded ${data.movies.size} movies", Toast.LENGTH_SHORT).show()
    }

    private fun showErrorState() {
        textViewStateIndicator.text = "Some Errors"
    }

    private fun showLoadingState() {
        textViewStateIndicator.text = "Loading in progress..."
    }
}
