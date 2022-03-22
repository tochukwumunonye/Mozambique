package com.tochukwu.mozambique.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tochukwu.mozambique.R
import com.tochukwu.mozambique.databinding.HomeFragmentBinding
import com.tochukwu.mozambique.util.Status
import com.tochukwu.mozambique.util.hideKeyboard
import com.tochukwu.mozambique.util.snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieFragment(
    var movieAdapter: MovieAdapter,
    var viewModel: MovieViewModel? = null
) : Fragment(R.layout.home_fragment){


    private var _binding: HomeFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModel ?:  ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)

        setupRecyclerView()
        subscribeToObserve()


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.btn.setOnClickListener {
            searchMovie()
        }
    }

    private fun searchMovie(){

        hideKeyboard()
        if(binding.editTxt.text.toString().isBlank()){
            Toast.makeText(activity, "Search For Nothing", Toast.LENGTH_SHORT).show()
            return
        }


        viewModel?.getMovies(binding.editTxt.text.toString())
    }


    private fun subscribeToObserve(){

        viewModel?.movieChannel?.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let{res->
                when(res.status){
                    Status.SUCCESS -> {
                        val data = res.data
                        movieAdapter.submitList(data)
                    }

                    Status.LOADING ->{


                        val data = res.data
                        movieAdapter.submitList(data)
                    }

                    Status.ERROR->{

                        val data = res.data
                      //  movieAdapter.submitList(data)
                        snackbar(res.message)
                        Toast.makeText(activity, res.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.searchrv.apply{
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = null
        }
    }

}




/**
@AndroidEntryPoint
class SearchFragment :Fragment(R.layout.fragment_search) {

@Inject
lateinit var userAdapter: UserAdapter

private val viewModel: SearchViewModel by viewModels()

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
super.onViewCreated(view, savedInstanceState)
setupRecyclerView()
subscribeToObservers()


var job: Job? = null
etSearch.addTextChangedListener {editable ->
job?.cancel()
job = lifecycleScope.launch {
delay(SEARCH_TIME_DELAY)
editable?.let {
viewModel.searchUser(it.toString())
}
}
}

userAdapter.setOnUserClickListener {user ->
findNavController().navigate(SearchFragmentDirections.globalActionToOthersProfileFragment(user.uid))
}
}

private fun subscribeToObservers() {
viewModel.searchResults.observe(viewLifecycleOwner, EventObserver(
onError = {
searchProgressBar.isVisible = false
snackbar(it)
},
onLoading = {
searchProgressBar.isVisible = true

}
){users ->
searchProgressBar.isVisible = false
userAdapter.users = users

})
}

private fun setupRecyclerView() = rvSearchResults.apply {
layoutManager = LinearLayoutManager(requireContext())
adapter = userAdapter
itemAnimator = null
}
}







-----TESTING IMPLEMENTATION





override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
super.onViewCreated(view, savedInstanceState)

viewModel =
viewModel ?: ViewModelProvider(requireActivity()).get(CharacterViewModel::class.java)

viewModel?.getDisney()
setUpObserver()
setUpRecyclerView()
}
private fun setUpObserver(){
viewModel?.disneyChannel?.observe(viewLifecycleOwner, {
it?.getContentIfNotHandled()?.let { res->
when(res.status){
Status.SUCCESS ->{
val data = res.data
disneyAdapter.submitList(data)
}
Status.LOADING ->{
val data = res.data
disneyAdapter.submitList(data)

}

Status.ERROR ->{
val data = res.data
disneyAdapter.submitList(data)
Toast.makeText(activity, res.message, Toast.LENGTH_SHORT).show()
}
}


}
})

}
private fun setUpRecyclerView() {
// disneyAdapter = DisneyAdapter()

binding.recycler.apply{
adapter = disneyAdapter
layoutManager = LinearLayoutManager(requireContext())
itemAnimator = null
}
}

}
/**
}

 ------MOVIE IMPLEMENTATION

super.onViewCreated(view, savedInstanceState)
viewModel = viewModel ?: ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
setupRecyclerView()
subscribeObservers()

val callback = object : OnBackPressedCallback(true) {
override fun handleOnBackPressed() {
findNavController().popBackStack()
}
}
requireActivity().onBackPressedDispatcher.addCallback(callback)

binding.btnSearchMovie.setOnClickListener {
searchMovie()
}
}

private fun subscribeObservers() {
viewModel?.movies?.observe(viewLifecycleOwner,  { event ->
event.getContentIfNotHandled()?.let {
when(it.status) {
Status.SUCCESS -> {
Timber.d("SUCCESS")
it.data?.let { movieList ->
addMovieListAdapter.movieItems = movieList
}
}

Status.ERROR -> {
Timber.e("Error fetching movies... ${it.message}")
}

Status.LOADING -> {
Timber.d("LOADING...")
}
}
}
})

}

private fun searchMovie() {
hideKeyboard()
if(binding.etMovieTitle.text.toString().isBlank()) {
Toast.makeText(activity, Constants.EMPTY_MOVIE_SEARCH, Toast.LENGTH_SHORT).show()
return
}
Timber.d("searchMovie...")
binding.focusableView.requestFocus()
viewModel?.getMovies(binding.etMovieTitle.text.toString())
}

private fun setupRecyclerView() {
binding.searchedMoviesRv.apply {
adapter = addMovieListAdapter
layoutManager = LinearLayoutManager(requireContext())
}
addMovieListAdapter.setOnItemFabClickListener {
viewModel?.insertMovie(it)
Toast.makeText(activity, Constants.ADDED_MOVIE_TO_DB, Toast.LENGTH_SHORT).show()
}

}

override fun onDestroyView() {
super.onDestroyView()
_binding = null
}
}**/