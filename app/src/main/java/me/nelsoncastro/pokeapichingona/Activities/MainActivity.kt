package me.nelsoncastro.pokeapichingona.Activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import me.nelsoncastro.pokeapichingona.Constants.AppConstants
import me.nelsoncastro.pokeapichingona.Fragments.MainContentFragment
import me.nelsoncastro.pokeapichingona.Fragments.MainListFragment
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.R
import me.nelsoncastro.pokeapichingona.ViewModel.MovieViewModel

class MainActivity : AppCompatActivity(), MainListFragment.ClickedMovieListener {

    private lateinit var mainFragment: MainListFragment
    private lateinit var mainContentFragment: MainContentFragment
    private val dummyMovies = arrayListOf(Movie())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val MovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        MovieViewModel.getAll().observe(this, Observer { result ->
            AppConstants.debugPreviewMovies(result)
            initFragments(result)
        })
    }

    fun initFragments(movieList: List<Movie>){
        mainFragment = MainListFragment.newInstance(movieList)
        var resource = 0
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            resource = R.id.portrait_main_place_holder
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            mainContentFragment = MainContentFragment.newInstance(Movie())
            changeFragment(R.id.land_main_movieviewer_ph, mainContentFragment)
            resource =R.id.land_main_place_holder
        }
        main_add_button.setOnClickListener { startActivity(Intent(this, NewMovieActivity::class.java)) }
        changeFragment(resource, mainFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    override fun searchMovie(movieName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun managePortraitItemClick(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun managedLandscapeItemClick(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
