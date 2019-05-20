package me.nelsoncastro.pokeapichingona.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.Models.MoviePreview
import me.nelsoncastro.pokeapichingona.Network.ApiFactory
import me.nelsoncastro.pokeapichingona.Repository.MovieRepository
import kotlin.coroutines.CoroutineContext

class MovieViewModel : ViewModel () {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository = MovieRepository(ApiFactory.ombdApi)

    val movieslist = MutableLiveData<MutableList<MoviePreview>>()

    fun fetchMovie(name: String){
        scope.launch {
            val movies = repository.getMoviesByName(name)
            movieslist.postValue(movies?: arrayListOf(MoviePreview(Title = "Dummy 1"), MoviePreview(Title = "Dummy 2")))
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}
