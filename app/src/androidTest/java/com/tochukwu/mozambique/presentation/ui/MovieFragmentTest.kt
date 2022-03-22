package com.tochukwu.mozambique.presentation.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.tochukwu.mozambique.FakeMovieDataAndroidTest.posters
import com.tochukwu.mozambique.R
import com.tochukwu.mozambique.launchFragmentInHiltsContainer
import com.tochukwu.mozambique.presentation.MovieAdapter
import com.tochukwu.mozambique.presentation.MovieFragment
import com.tochukwu.mozambique.presentation.MovieFragmentDirections
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import javax.inject.Inject


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MovieFragmentTest {



    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var testFragmentFactory: TestFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }


    //Navigate From Fragment to Fragment

    @Test
    fun recycler_view_click_navigates(){
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltsContainer<MovieFragment>(
            fragmentFactory = testFragmentFactory
        ){
            movieAdapter.submitList(posters)
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.onView(withId(R.id.searchrv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MovieAdapter.MovieViewHolder>(
            0, ViewActions.click()
        ))


        verify(navController).navigate(MovieFragmentDirections.actionMovieFragmentToDetailFragment(posters[0]))

    }
}
