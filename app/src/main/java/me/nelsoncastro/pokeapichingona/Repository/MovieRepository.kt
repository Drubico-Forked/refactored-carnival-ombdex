package me.nelsoncastro.pokeapichingona.Repository

import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.Network.OmbdApi

class MovieRepository(private val api: OmbdApi) : BaseRepository() {

    suspend fun getMovieByName(name: String) : Movie? {
        return safeApiCall(
                call = { api.getMovieByName(name).await()},
                errorMessage = "Error Fetching Movie by Name"
        )
    }
}