package com.csi.bottomnavigationactivity.ui.home

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.csi.bottomnavigationactivity.AddEditNoteActivity
import com.csi.bottomnavigationactivity.NotificationActivity
import com.csi.bottomnavigationactivity.R
import com.csi.bottomnavigationactivity.databinding.FragmentHomeBinding
import com.csi.bottomnavigationactivity.db.Note
import com.csi.bottomnavigationactivity.service.MyService
import com.csi.bottomnavigationactivity.utils.NoteClickDeleteInterface
import com.csi.bottomnavigationactivity.utils.NoteClickInterface
import com.csi.bottomnavigationactivity.utils.NoteRVAdapter
import com.csi.bottomnavigationactivity.utils.showToast
import okhttp3.internal.notify
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment(), NoteClickInterface, NoteClickDeleteInterface {

    companion object {
        const val CHANNEL_ID = "999"
    }

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

        createNotificationChannel()
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
        binding.sendNotification.setOnClickListener {
            requireActivity().startActivity(Intent(requireContext(), NotificationActivity::class.java))
        }
    }


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Name"
            val descriptionText = "Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
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