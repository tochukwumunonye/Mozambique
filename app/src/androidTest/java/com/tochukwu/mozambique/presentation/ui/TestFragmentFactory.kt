package com.tochukwu.mozambique.presentation.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.tochukwu.mozambique.presentation.MovieAdapter
import com.tochukwu.mozambique.presentation.MovieFragment
import com.tochukwu.mozambique.presentation.MovieViewModel
import com.tochukwu.mozambique.presentation.detailPresentation.DetailFragment
import com.tochukwu.mozambique.presentation.detailPresentation.DetailViewModel
import com.tochukwu.mozambique.repository.FakeRepositoryAndroidTest
import javax.inject.Inject

class TestFragmentFactory @Inject constructor(
    private val movieListAdapter : MovieAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            MovieFragment::class.java.name -> MovieFragment(movieListAdapter, MovieViewModel(FakeRepositoryAndroidTest()))
            DetailFragment::class.java.name -> DetailFragment(DetailViewModel(FakeRepositoryAndroidTest()))
            else -> super.instantiate(classLoader, className)
        }
    }


}




/**
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.tochukwu.moviepractice.presentation.addMovies.AddMovieFragment
import com.tochukwu.moviepractice.presentation.addMovies.AddMovieListAdapter
import com.tochukwu.moviepractice.presentation.movies.MovieAdapter
import com.tochukwu.moviepractice.presentation.movies.MovieFragment
import com.tochukwu.moviepractice.presentation.movies.MovieViewModel
import com.tochukwu.moviepractice.repository.FakeMovieRepositoryAndroidTest
import javax.inject.Inject



MovieFragment::class.java.name -> MovieFragment(
movieAdapter, MovieViewModel(FakeMovieRepositoryAndroidTest())
)
AddMovieFragment::class.java.name -> AddMovieFragment(
addMovieListAdapter, MovieViewModel(FakeMovieRepositoryAndroidTest())
)

else -> super.instantiate(classLoader, className)
}
}
} **/