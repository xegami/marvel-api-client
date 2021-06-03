package com.xegami.mac.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.xegami.mac.R
import com.xegami.mac.listener.CharacterClickListener
import com.xegami.mac.rest.dto.CharacterDTO

class CharacterRecyclerViewAdapter(
    private val context: Context,
    private var characters: MutableList<CharacterDTO>,
    private var charactersCopy: List<CharacterDTO> = characters,
    private val cListener: CharacterClickListener
) :
    RecyclerView.Adapter<CharacterRecyclerViewAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row_character, parent, false)
        return CharacterViewHolder(view, cListener)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }

    fun filterByName(query: String) {
        characters = mutableListOf()

        if (query.isEmpty()) {
            characters.addAll(charactersCopy)
        } else {
            charactersCopy.forEach { character ->
                if (character.name.toLowerCase().contains(query)) characters.add(character)
            }
        }

        notifyDataSetChanged()
    }

    class CharacterViewHolder(itemView: View, listener: CharacterClickListener) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tv_list_row_character_name)
        private val tvDescription = itemView.findViewById<TextView>(R.id.tv_list_row_character_description)
        private val ivThumbnail = itemView.findViewById<ImageView>(R.id.iv_list_row_character_thumbnail)
        private val cListener = listener

        fun bind(character: CharacterDTO) {
            tvName.text = character.name
            tvDescription.text = if (character.description.isEmpty()) itemView.context.getString(R.string.no_description) else character.description
            Picasso.with(itemView.context).load("${character.thumbnail.path}/landscape_medium.${character.thumbnail.extension}").into(ivThumbnail)
            itemView.setOnClickListener { cListener.onClick(character) }
        }
    }

}