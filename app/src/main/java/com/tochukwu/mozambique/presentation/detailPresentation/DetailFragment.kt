package com.tochukwu.mozambique.presentation.detailPresentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.tochukwu.mozambique.R
import com.tochukwu.mozambique.databinding.DetailLayoutBinding
import com.tochukwu.mozambique.domain.model.Movie
import com.tochukwu.mozambique.domain.model.movieDetailDomain.Details
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment(
    var viewModel: DetailViewModel? = null
) : Fragment(R.layout.detail_layout) {

    private val args: DetailFragmentArgs by navArgs()
    private val movieDetailz: Movie by lazy{
        args.selectedinfo
    }


    private var _binding: DetailLayoutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = viewModel ?: ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)

       // viewModel?.fetchDetail(movieDetailz.imdbID)

       // viewModel?.imdbId?.observe(viewLifecycleOwner){poster ->
         //   displayPost(poster)

      //  }
    }

    private fun displayPost(poster: Details?) {

        binding.apply{
            detailtv.text = poster?.Title
        }
    }
}







