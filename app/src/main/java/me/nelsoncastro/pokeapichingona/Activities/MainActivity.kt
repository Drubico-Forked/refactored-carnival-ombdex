package me.nelsoncastro.pokeapichingona.Activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movies_list_fragment.*
import me.nelsoncastro.pokeapichingona.Constants.AppConstants
import me.nelsoncastro.pokeapichingona.Fragments.MainContentFragment
import me.nelsoncastro.pokeapichingona.Fragments.MainListFragment
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.R
import me.nelsoncastro.pokeapichingona.ViewModel.MovieViewModel

class MainActivity : AppCompatActivity(), MainListFragment.ClickedMovieListener {

    private lateinit var mainFragment: MainListFragment
    private lateinit var mainContentFragment: MainContentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarmain)
        initFragments()
    }

    fun initFragments(){
        mainFragment = MainListFragment()
        var resource = 0
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            resource = R.id.portrait_main_place_holder
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            mainContentFragment = MainContentFragment.newInstance(Movie())
            changeFragment(R.id.land_main_movieviewer_ph, mainContentFragment)
            resource =R.id.land_main_place_holder
        }
        val intent = Intent(this, NewMovieActivity::class.java)
        main_add_button.setOnClickListener { startActivityForResult(intent , AppConstants.ADD_TASK_REQUEST) }
        changeFragment(resource, mainFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    override fun managePortraitItemClick(movie: Movie) {
        mainContentFragment = MainContentFragment.newInstance(movie)
        changeFragment(R.id.portrait_main_place_holder, mainContentFragment)
    }

    override fun managedLandscapeItemClick(movie: Movie) {
        mainContentFragment = MainContentFragment.newInstance(movie)
        changeFragment(R.id.land_main_movieviewer_ph, mainContentFragment)
    }

}
