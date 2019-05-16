package me.nelsoncastro.pokeapichingona.Network

import kotlinx.coroutines.Deferred
import me.nelsoncastro.pokeapichingona.Models.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OmbdApi {

    @GET("t={name}")
    fun getMovieByName(@Path("name")name: String): Deferred<Response<Movie>>

}
