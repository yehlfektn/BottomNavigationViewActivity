package com.csi.bottomnavigationactivity.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.csi.bottomnavigationactivity.databinding.FragmentHomeBinding
import com.csi.bottomnavigationactivity.databinding.FragmentMoviesBinding
import com.csi.bottomnavigationactivity.databinding.FragmentMoviesDetailBinding
import com.csi.bottomnavigationactivity.utils.MoviesRVAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MoviesDetailFragment : Fragment() {

    private var _binding: FragmentMoviesDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("Title")
        val description = arguments?.getString("Description")
        val image = arguments?.getString("Image")

        activity?.title = title
        binding.title.text = title
        binding.description.text = description
        Glide.with(binding.imageView)
            .load(image)
            .into(binding.imageView)
    }
}