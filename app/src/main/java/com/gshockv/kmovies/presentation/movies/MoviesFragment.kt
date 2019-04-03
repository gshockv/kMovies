package com.gshockv.kmovies.presentation.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gshockv.kmovies.R
import com.gshockv.kmovies.data.model.MoviesResponse
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movies_list.*
import javax.inject.Inject

class MoviesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel : MoviesViewModel

    private val moviesAdapter = MoviesAdapter()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)
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

        viewModel.store.observe(viewLifecycleOwner) {
            when (it) {
                is MoviesViewState.LoadingState -> showLoadingState()
                is MoviesViewState.ErrorState -> showErrorState()
                is MoviesViewState.DataState -> showDataState(it.data)
            }
        }

        viewModel.loadMoviesList()
    }

    private fun setupRecyclerView() {
        recyclerViewMovies.layoutManager = LinearLayoutManager(context)
        recyclerViewMovies.adapter = moviesAdapter
    }

    private fun showDataState(data: MoviesResponse) {
        moviesAdapter.updateData(data.movies)
        swipeRefresh.isRefreshing = false

        Toast.makeText(context, "Page: ${data.page}", Toast.LENGTH_SHORT).show()
    }

    private fun showErrorState() {
        swipeRefresh.isRefreshing = false
    }

    private fun showLoadingState() {
        swipeRefresh.isRefreshing = true
    }
}
