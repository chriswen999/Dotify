package com.example.dotify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.dotify.databinding.ItemPersonBinding
import com.example.dotify.model.Song

class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.personViewHolder>() {
    private lateinit var binding: ItemPersonBinding
    var onPersonClickListener: (song: Song) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): personViewHolder {

        binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context))
        return personViewHolder(binding)
    }

    override fun onBindViewHolder(holder: personViewHolder, position: Int) {
        val song = listOfSongs[position]
        with(holder.binding){
            tvName.text = song.title
            tvArtist.text = song.artist
            profilePic.setImageResource(song.smallImageURL)

            itemRoot.setOnClickListener{
                onPersonClickListener(song)
            }

        }
    }

    fun updatePeople(newListOfSongs: List<Song>){
        this.listOfSongs = newListOfSongs
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int = listOfSongs.size

    class personViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)


}


