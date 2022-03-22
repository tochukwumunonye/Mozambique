package com.tochukwu.mozambique.presentation.detailPresentation

import androidx.lifecycle.*
import com.tochukwu.mozambique.domain.model.Movie
import com.tochukwu.mozambique.domain.model.movieDetailDomain.Details
import com.tochukwu.mozambique.domain.repository.MainRepository
import com.tochukwu.mozambique.util.Event
import com.tochukwu.mozambique.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel(){


    private val _imdbId = MutableLiveData<String>()

    val imdbId: LiveData<Details> = _imdbId.switchMap { id->
        repository.getMovieDetail(id).asLiveData()
    }

     fun fetchDetail(id: String?){
        _imdbId.value = id!!
    }
}


