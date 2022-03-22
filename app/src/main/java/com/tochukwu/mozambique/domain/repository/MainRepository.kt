package com.tochukwu.mozambique.domain.repository

import com.tochukwu.mozambique.data.remote.dto.Search
import com.tochukwu.mozambique.domain.model.Movie
import com.tochukwu.mozambique.domain.model.movieDetailDomain.Details
import com.tochukwu.mozambique.util.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {



    fun searchMovie(word: String) : Flow<Resource<List<Movie>>>

    fun getMovieDetail(id:String) : Flow<Details>
}


/**
 *
 * interface MainRepository {

fun getPoster() : Flow<Resource<List<PosterDtoItem>>>

fun getPosterById(id: Int?) : Flow<PosterDtoItem>
}
        **/