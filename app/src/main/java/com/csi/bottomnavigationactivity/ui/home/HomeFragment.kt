package com.csi.bottomnavigationactivity.ui.home

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
import com.csi.bottomnavigationactivity.databinding.FragmentHomeBinding
import com.csi.bottomnavigationactivity.db.Note
import com.csi.bottomnavigationactivity.service.MyService
import com.csi.bottomnavigationactivity.utils.NoteClickDeleteInterface
import com.csi.bottomnavigationactivity.utils.NoteClickInterface
import com.csi.bottomnavigationactivity.utils.NoteRVAdapter
import com.csi.bottomnavigationactivity.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment(), NoteClickInterface, NoteClickDeleteInterface {

    private val homeViewModel by viewModel<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.e("onCreateView()")

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // on below line we are initializing our adapter class.
        val noteRVAdapter = NoteRVAdapter(this, this)

        // on below line we are setting
        // adapter to our recycler view.
        binding.notesRV.adapter = noteRVAdapter

        // on below line we are calling all notes method
        // from our view modal class to observer the changes on list.
        homeViewModel.allNotes.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                noteRVAdapter.updateList(it)
            }
        })

        binding.idFAB.setOnClickListener {
            // adding a click listener for fab button
            // and opening a new intent to add a new note.
            val intent = Intent(requireContext(), AddEditNoteActivity::class.java)
            startActivity(intent)
        }

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
                uri ->
            Glide.with(requireContext())
                .load(uri)
                .into(binding.image)
        }
        val intent = Intent(requireContext(), MyService::class.java)

        binding.startService.setOnClickListener {
            requireActivity().startService(intent)
        }
        binding.stopService.setOnClickListener {
            requireActivity().stopService(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.e("onDestroyView()")
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        Timber.e("OnStart()")
    }

    override fun onResume() {
        super.onResume()
        Timber.e("OnResume()")
    }

    override fun onPause() {
        super.onPause()
        Timber.e("onPause()")
    }

    override fun onStop() {
        super.onStop()
        Timber.e("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("onDestroy()")
    }

    override fun onNoteClick(note: Note) {
        // opening a new intent and passing a data to it.
        val intent = Intent(requireContext(), AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
    }

    override fun onDeleteIconClick(note: Note) {
        // in on note click method we are calling delete
        // method from our view modal to delete our not.
        homeViewModel.deleteNote(note)
        // displaying a toast message
        showToast("${note.noteTitle} Deleted")
    }
}