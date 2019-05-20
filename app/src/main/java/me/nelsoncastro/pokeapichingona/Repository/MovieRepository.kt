package me.nelsoncastro.pokeapichingona.Repository

import me.nelsoncastro.pokeapichingona.Constants.AppConstants
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.Models.MoviePreview
import me.nelsoncastro.pokeapichingona.Network.OmbdApi

class MovieRepository(private val api: OmbdApi) : BaseRepository() {

    suspend fun getMoviesByName(name: String) : MutableList<MoviePreview>? {
        val moviesResponse = safeApiCall(
                call = { api.getMoviesByName(name).await()},
                errorMessage = "Error Fetching Movie by Name"
        )
        return moviesResponse?.Search?.toMutableList()
    }
}