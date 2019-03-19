package com.gshockv.kmovies.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gshockv.kmovies.BuildConfig
import com.gshockv.kmovies.R
import com.gshockv.kmovies.data.model.MoviesList
import kotlinx.android.synthetic.main.fragment_movies_list.*

class MoviesListFragment : Fragment() {
    private lateinit var viewModel : MoviesListViewModel

    private val moviesAdapter = MoviesAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this).get(MoviesListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()

        swipeRefresh.setOnRefreshListener {
            // TODO: Implement me...
            swipeRefresh.isRefreshing = false
        }

        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is MoviesUiState.LoadingState -> showLoadingState()
                is MoviesUiState.ErrorState -> showErrorState()
                is MoviesUiState.DataState -> showDataState(it.data)
            }
        })

        viewModel.loadMoviesList()
    }

    private fun setupRecyclerView() {
        recyclerViewMovies.layoutManager = LinearLayoutManager(context)
        recyclerViewMovies.adapter = moviesAdapter
    }

    private fun showDataState(data: MoviesList) {
        moviesAdapter.updateData(data.movies)
        swipeRefresh.isRefreshing = false
    }

    private fun showErrorState() {
        swipeRefresh.isRefreshing = false
    }

    private fun showLoadingState() {
        swipeRefresh.isRefreshing = true
    }
}
