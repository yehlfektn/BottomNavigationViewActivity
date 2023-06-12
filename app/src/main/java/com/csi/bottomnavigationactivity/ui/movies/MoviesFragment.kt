package com.csi.bottomnavigationactivity.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.csi.bottomnavigationactivity.R
import com.csi.bottomnavigationactivity.databinding.FragmentHomeBinding
import com.csi.bottomnavigationactivity.databinding.FragmentMoviesBinding
import com.csi.bottomnavigationactivity.network.Search
import com.csi.bottomnavigationactivity.utils.MoviesRVAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MoviesFragment : Fragment() {

    private val moviesViewModel by viewModel<MoviesViewModel>()
    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // on below line we are initializing our adapter class.
        val moviesAdapter = MoviesRVAdapter{
            openMoviesDetailFragment(it)
        }
        // on below line we are setting
        // adapter to our recycler view.
        binding.notesRV.adapter = moviesAdapter
        moviesViewModel.movieResult.observe(viewLifecycleOwner) { list ->
            list?.let {
                // on below line we are updating our list.
                moviesAdapter.updateList(it.Search)
            }
        }

        binding.moviesButton.setOnClickListener {
            moviesViewModel.getMovies(binding.moviePrompt.text.toString())
        }
    }

    private fun openMoviesDetailFragment(movie: Search) {
        val bundle = bundleOf()
        bundle.putString("Title", movie.Title)
        bundle.putString("Description", movie.Year)
        bundle.putString("Image", movie.Poster)
        findNavController().navigate(R.id.moviesDetailFragment, bundle)
    }
}