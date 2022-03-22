package com.tochukwu.mozambique.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tochukwu.mozambique.domain.repository.MainRepository
import com.tochukwu.mozambique.data.remote.dto.Search
import com.tochukwu.mozambique.domain.model.Movie
import com.tochukwu.mozambique.util.Event
import com.tochukwu.mozambique.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
   private val movieRepo: MainRepository
): ViewModel(){


    private val _movieChannel = MutableLiveData<Event<Resource<List<Movie>>>>()

      val movieChannel: LiveData<Event<Resource<List<Movie>>>> get() = _movieChannel

    fun getMovies(searchQuery: String)  = viewModelScope.launch{
        movieRepo.searchMovie(searchQuery).collectLatest{
            _movieChannel.postValue(Event(it))
        }
    }


}

/**
x.inject.Inject

fun getDisney() = viewModelScope.launch{

characterRepo.getPoster().collectLatest{
_disneyChannel.pos

    private val _moviesFromDb = MutableLiveData<List<Movie>>()
    val moviesFromDb: LiveData<List<Movie>> get() = _moviesFromDb

    fun getMovies(searchQuery: String) {
        Timber.d("getMovies...")
        _movies.postValue(Event(Resource.loading(null)))
        if(searchQuery.isBlank()) {
            _movies.postValue(Event(Resource.error("The fields must not be empty", null)))
            return
        }
        if(searchQuery.length > Constants.MAX_MOVIE_TITLE_LENGTH) {
            _movies.postValue(Event(Resource.error("The title can't be longer than ${Constants.MAX_MOVIE_TITLE_LENGTH}", null)))
            return
        }
        getMoviesFromNetwork(searchQuery)
    }

    private fun getMoviesFromNetwork(searchQuery: String) = viewModelScope.launch {
        movieRepository.getMovies(searchQuery).collect {
            _movies.postValue(Event(it))
        }
    }

    fun returnAllMoviesFromDb() = viewModelScope.launch {
        movieRepository.returnAllMovies().collect {
            _moviesFromDb.postValue(it)
        }
    }

    fun insertMovieList(movieList: List<Movie>) = viewModelScope.launch {
        movieRepository.insertMovieList(movieList = movieList)
    }

    fun insertMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.insertMovie(movie)
    }

    fun deleteMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.deleteMovie(movie)
    }

    fun deleteMovieList(movieList: List<Movie>) = viewModelScope.launch {
        movieRepository.deleteMovieList(movieList)
    }**/