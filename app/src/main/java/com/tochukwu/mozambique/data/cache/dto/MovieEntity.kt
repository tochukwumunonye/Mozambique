package com.tochukwu.mozambique.data.cache.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tochukwu.mozambique.domain.model.Movie

@Entity(tableName = "movieTable")
data class MovieEntity(

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "year")
    val year: String,

    @ColumnInfo(name = "poster")
    val poster: String,



    @PrimaryKey
    val id: Int? = null

) {


    fun toMovie(): Movie {

        return Movie(
            title = title,
            year = year,
            poster = poster,

        )
    }
}



/**

interface MovieRepository {

suspend fun insertMovieList(movieList: List<Movie>)

suspend fun insertMovie(movie: Movie)

suspend fun deleteMovie(movie: Movie)

suspend fun deleteMovieList(movieList: List<Movie>)

fun returnAllMovies(): Flow<List<Movie>>

fun getMovies(
movieSearchQuery: String
): Flow<Resource<List<Movie>>>  **/