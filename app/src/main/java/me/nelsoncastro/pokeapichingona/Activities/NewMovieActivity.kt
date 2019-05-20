package me.nelsoncastro.pokeapichingona.Activities

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.preview_add_movie.*
import me.nelsoncastro.pokeapichingona.Adapters.RVPreviewAdapter
import me.nelsoncastro.pokeapichingona.Models.MoviePreview
import me.nelsoncastro.pokeapichingona.R
import me.nelsoncastro.pokeapichingona.ViewModel.MovieViewModel

class NewMovieActivity : AppCompatActivity(){

    val emptymoviespreview = ArrayList<MoviePreview>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview_add_movie)

        val MovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val recyclerView = rv_preview
        val moviesPreviewAdapter = RVPreviewAdapter(movies = emptymoviespreview,
            clickListener = { movie:MoviePreview, checky: View ->
                movie.selected = !movie.selected
                Toast.makeText(this, if (movie.selected) "Selected ${movie.Title}" else "Unselected ${movie.Title}", Toast.LENGTH_SHORT).show()
                if (movie.selected) checky.visibility = View.VISIBLE else checky.visibility = View.GONE
            })

        recyclerView.apply {
            adapter = moviesPreviewAdapter
            setHasFixedSize(true)
            this.layoutManager = layoutManager
        }

        MovieViewModel.movieslist.observe(this, Observer { result ->
                    moviesPreviewAdapter.changeDataSet(result)
        })

        bt_search.setOnClickListener {
            val movieNameQuery = et_search.text.toString()
            if (movieNameQuery.isNotEmpty() && movieNameQuery.isNotBlank()) {
                MovieViewModel.fetchMovie(movieNameQuery)
                MovieViewModel.movieslist.observe(this, Observer { result ->
                    moviesPreviewAdapter.changeDataSet(result)
               })
            }
        }

        bt_cancel.setOnClickListener {
            et_search.text.clear()
            moviesPreviewAdapter.changeDataSet(emptymoviespreview)
        }

        bt_add_preview.setOnClickListener {

        }

    }

    fun debugPreviewMovies(result: MutableList<MoviePreview>){
        Log.d("PETROLERO", "__________________________________________________")
        for (res in result) Log.d("PETROLERO", "${res.Title} -> selected = ${res.selected}")
        Log.d("PETROLERO", "__________________________________________________")
    }

}