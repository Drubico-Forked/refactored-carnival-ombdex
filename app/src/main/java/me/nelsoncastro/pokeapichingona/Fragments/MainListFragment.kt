package me.nelsoncastro.pokeapichingona.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.nelsoncastro.pokeapichingona.Adapters.RVMovieAdapter
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.R
import java.lang.RuntimeException

class MainListFragment: Fragment() {

    private lateinit var movies : ArrayList<Movie>
    private lateinit var moviesAdapter: RVMovieAdapter
    var listenerTool : ClickedMovieListener? = null

    interface ClickedMovieListener{
        fun searchMovie(movieName: String)
        fun managePortraitItemClick(movie: Movie)
        fun managedLandscapeItemClick(movie: Movie)
    }

    override fun onAttach(context: Context?) {
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
        return view
    }

}
