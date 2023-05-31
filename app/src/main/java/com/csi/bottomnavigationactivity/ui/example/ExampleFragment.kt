package com.csi.bottomnavigationactivity.ui.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.csi.bottomnavigationactivity.AddEditNoteActivity
import com.csi.bottomnavigationactivity.R
import com.csi.bottomnavigationactivity.databinding.FragmentExampleBinding
import com.csi.bottomnavigationactivity.databinding.FragmentHomeBinding
import com.csi.bottomnavigationactivity.db.Note
import com.csi.bottomnavigationactivity.utils.NoteClickDeleteInterface
import com.csi.bottomnavigationactivity.utils.NoteClickInterface
import com.csi.bottomnavigationactivity.utils.NoteRVAdapter
import com.csi.bottomnavigationactivity.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ExampleFragment : Fragment() {

    private var _binding: FragmentExampleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startActivity.setOnClickListener {
            findNavController().navigate(R.id.secondExampleFragment)
        }
        binding.text.text = "This is ExampleFragment"
    }
}