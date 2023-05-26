package com.csi.bottomnavigationactivity.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.csi.bottomnavigationactivity.R
import com.csi.bottomnavigationactivity.db.Note
import com.csi.bottomnavigationactivity.network.IMDBResult
import com.csi.bottomnavigationactivity.network.Search

class MoviesRVAdapter() :
    RecyclerView.Adapter<MoviesRVAdapter.ViewHolder>() {

    // on below line we are creating a
    // variable for our all notes list.
    private val allNotes = ArrayList<Search>()

    // on below line we are creating a view holder class.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are creating an initializing all our
        // variables which we have added in layout file.
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating our layout file for each item of recycler view.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.movies_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = allNotes[position]
        holder.title.text = item.Title
        holder.description.text = item.Year

        Glide.with(holder.imageView.context)
            .load(item.Poster)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    // below method is use to update our list of notes.
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Search>) {
        // on below line we are clearing
        // our notes array list
        allNotes.clear()
        // on below line we are adding a
        // new list to our all notes list.
        allNotes.addAll(newList)
        // on below line we are calling notify data
        // change method to notify our adapter.
        notifyDataSetChanged()
    }
}
