package me.nelsoncastro.pokeapichingona.Activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import me.nelsoncastro.pokeapichingona.Fragments.MainContentFragment
import me.nelsoncastro.pokeapichingona.Fragments.MainListFragment
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.R

class MainActivity : AppCompatActivity(), MainListFragment.ClickedMovieListener {


    private lateinit var mainFragment: MainListFragment
    private lateinit var mainContentFragment: MainContentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun initFragments(movieList: ArrayList<Movie>){
        mainFragment = MainListFragment.newInstance(movieList)

        val resource = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            R.id.portrait_main_place_holder
        } else {
            mainContentFragment = MainContentFragment.newInstance(Movie())
            changeFragment(R.id.land_main_movieviewer_ph, mainContentFragment)
            R.id.land_main_place_holder
        }
        changeFragment(resource, mainFragment)
    }

    fun initPreviewNewMovie(){
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
