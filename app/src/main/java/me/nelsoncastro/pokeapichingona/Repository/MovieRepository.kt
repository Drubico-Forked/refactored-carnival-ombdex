package me.nelsoncastro.pokeapichingona.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import me.nelsoncastro.pokeapichingona.Constants.AppConstants
import me.nelsoncastro.pokeapichingona.Database.Domain.MovieDao
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.Models.MoviePreview
import me.nelsoncastro.pokeapichingona.Network.OmbdApi

class MovieRepository(private val movieDao: MovieDao, private val api: OmbdApi) : BaseRepository() {

    suspend fun getMoviesByName(name: String) : MutableList<MoviePreview>? {
        val moviesResponse = safeApiCall(
                call = { api.getMoviesByName(name).await()},
                errorMessage = "Error Fetching Movies by Name"
        )
        return moviesResponse?.Search?.toMutableList()
    }

    suspend fun getMovieByTitle(name: String) : Movie? {
        val movieResponse = safeApiCall(
            call = { api.getMovieByTitle(name).await()},
            errorMessage = "Error Fetching Movie by Title"
        )
        return movieResponse
    }

    @WorkerThread
    suspend fun insert(movie: Movie) = movieDao.insertMovie(movie)

    fun getAllfromRoomDB():LiveData<List<Movie>> = movieDao.loadAllMovies()

    fun getMovieByName(name: String) = movieDao.searchMovieByName(name)
}