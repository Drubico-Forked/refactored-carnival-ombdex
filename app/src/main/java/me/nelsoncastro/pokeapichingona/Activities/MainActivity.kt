package me.nelsoncastro.pokeapichingona.Activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import me.nelsoncastro.pokeapichingona.Fragments.MainContentFragment
import me.nelsoncastro.pokeapichingona.Fragments.MainListFragment
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.R

class MainActivity : AppCompatActivity(), MainListFragment.ClickedMovieListener {

    private lateinit var mainFragment: MainListFragment
    private lateinit var mainContentFragment: MainContentFragment
    private val dummyMovies = arrayListOf(Movie())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragments(dummyMovies)
    }

    fun initFragments(movieList: ArrayList<Movie>){
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
