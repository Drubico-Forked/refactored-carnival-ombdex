package me.nelsoncastro.pokeapichingona.Fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.movies_list_fragment.view.*
import me.nelsoncastro.pokeapichingona.Adapters.RVMovieAdapter
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.R
import java.lang.RuntimeException

class MainListFragment: Fragment() {

    private lateinit var movies : List<Movie>
    private lateinit var moviesAdapter: RVMovieAdapter
    var listenerTool : ClickedMovieListener? = null

    companion object {
        fun newInstance(dataset: List<Movie>): MainListFragment{
            return MainListFragment().apply {
                movies = dataset
            }
        }
    }

    interface ClickedMovieListener{
        fun searchMovie(movieName: String)
        fun managePortraitItemClick(movie: Movie)
        fun managedLandscapeItemClick(movie: Movie)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickedMovieListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de la interfaz")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.movies_list_fragment, container, false)

        initRecyclerView(resources.configuration.orientation, view)

        return view
    }

    fun initRecyclerView(orientation: Int, container: View) {
        val linearLayoutManager = LinearLayoutManager(this.context)
        val recyclerview  = container.rv_list

        moviesAdapter = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            RVMovieAdapter(movies = movies, clickListener = {movie: Movie -> listenerTool?.managePortraitItemClick(movie)})
        }else {
            RVMovieAdapter(movies = movies, clickListener = {movie: Movie -> listenerTool?.managedLandscapeItemClick(movie)})
        }

        recyclerview.apply {
            adapter = moviesAdapter
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    fun updateMoviesAdapter(movieList: ArrayList<Movie>) { moviesAdapter.changeDataSet(movieList) }
}
