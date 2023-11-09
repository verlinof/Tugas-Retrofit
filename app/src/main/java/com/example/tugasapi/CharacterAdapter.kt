package com.example.tugasapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugasapi.Models.CharacterData


class CharacterAdapter(private val characterList : ArrayList<CharacterData>):
    RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.character_layout, parent, false)

        return MyViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = characterList[position]
        holder.tvName.text = currentItem.name
        holder.tvSpecies.text = currentItem.species
        Glide.with(holder.characterImage)
            .load(currentItem.image)
            .centerCrop()
            .placeholder(R.drawable.placeholder_image)
            .into(holder.characterImage)
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        val characterImage: ImageView = itemView.findViewById(R.id.characterImage)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvSpecies: TextView = itemView.findViewById(R.id.tvSpecies)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }


}