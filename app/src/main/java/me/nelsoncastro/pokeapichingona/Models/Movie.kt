package me.nelsoncastro.pokeapichingona.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
        val Title:String = "N/A",
        val Year:String = "N/A",
        val Released: String = "N/A",
        val Runtime:String = "N/A",
        val Genre:String = "N/A",
        val Director:String = "N/A",
        val Actors:String = "N/A",
        val Plot:String = "N/A",
        val Language:String = "N/A",
        val imdbRating:String = "N/A",
        val Poster:String = "N/A"
){
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0
}

data class MoviePreview(
        val Title: String = "N/A",
        val Year: String = "N/A",
        val imdbID: String = "N/A",
        val Type: String = "N/A",
        val Poster: String = "N/A",
        var selected: Boolean = false
)

data class OmbdMovieResponse (
        val Search: List<MoviePreview>,
        val totalResults: String,
        val Response: String
)