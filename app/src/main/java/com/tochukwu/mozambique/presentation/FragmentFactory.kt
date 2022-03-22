package com.tochukwu.mozambique.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class FragmentFactory @Inject constructor(
    private val movieAdapter: MovieAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            MovieFragment::class.java.name -> MovieFragment(movieAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}