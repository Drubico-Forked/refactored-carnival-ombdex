package me.nelsoncastro.pokeapichingona.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.R

class MainContentFragment: Fragment() {

    var movie = Movie()

    companion object {
        fun newInstance(dataset: Movie): MainContentFragment {
            return MainContentFragment().apply {
                movie = dataset
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.movie_viewer, container, false)

        bindData(view, movie)

        return view
    }

    fun bindData(view: View, data: Movie){
    }
}