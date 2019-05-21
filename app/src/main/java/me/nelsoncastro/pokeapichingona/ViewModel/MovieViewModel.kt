package me.nelsoncastro.pokeapichingona.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import me.nelsoncastro.pokeapichingona.Database.MainDatabase
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.Models.MoviePreview
import me.nelsoncastro.pokeapichingona.Network.ApiFactory
import me.nelsoncastro.pokeapichingona.Repository.MovieRepository

class MovieViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: MovieRepository

    init {
        val movieDao = MainDatabase.getDatabase(app).movieDao()
        repository = MovieRepository(movieDao, ApiFactory.ombdApi)
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    private val movieslist = MutableLiveData<MutableList<MoviePreview>>()

    private val movieResult = MutableLiveData<Movie>()

    fun fetchMovie(name: String){
        scope.launch {
            val movies = repository.getMoviesByName(name)
            movieslist.postValue(movies?: arrayListOf(MoviePreview(Title = "Dummy 1"), MoviePreview(Title = "Dummy 2")))
        }
    }

    fun getMovieListVM(): LiveData<MutableList<MoviePreview>> = movieslist

    fun fetchMovieByTitle(name: String){
        scope.launch {
            val movie = repository.getMovieByTitle(name)
            movieResult.postValue(movie)
        }
    }

    fun getMovieResult(): LiveData<Movie> = movieResult


    fun insert(movie: Movie) = scope.launch {
        repository.insert(movie)
    }

    fun getAll():LiveData<List<Movie>> = repository.getAllfromRoomDB()

    //fun cancelAllRequests() = Dispatchers.IO.cancel()

}
