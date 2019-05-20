package me.nelsoncastro.pokeapichingona.Activities

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.preview_add_movie.*
import me.nelsoncastro.pokeapichingona.Adapters.RVMovieAdapter
import me.nelsoncastro.pokeapichingona.Adapters.RVPreviewAdapter
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.Models.MoviePreview
import me.nelsoncastro.pokeapichingona.R
import me.nelsoncastro.pokeapichingona.ViewModel.MovieViewModel

class NewMovieActivity : AppCompatActivity(){
    val moviespreview = ArrayList<Movie>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview_add_movie)
        val MovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        bt_search.setOnClickListener {
            val movieNameQuery = et_search.text.toString()
            if (movieNameQuery.isNotEmpty() && movieNameQuery.isNotBlank()) {
                MovieViewModel.fetchMovie(movieNameQuery)
                MovieViewModel.movieslist.observe(this, Observer { result ->
                    val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                    val recyclerView = rv_preview
                    val moviesPreviewAdapter = RVPreviewAdapter(movies = result,
                        clickListener = { movie:MoviePreview -> Toast.makeText(this, "Selected ${movie.Title}", Toast.LENGTH_SHORT).show()})
                    recyclerView.apply {
                        adapter = moviesPreviewAdapter
                        setHasFixedSize(true)
                        this.layoutManager = layoutManager
                    }
                })
            }

        }
    }
}