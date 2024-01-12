package com.ukmtechcode.projekmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BeritaListAdapter(private val listBerita:List<Berita>): RecyclerView.Adapter<BeritaListAdapter.ListViewHolder>(){
    var clickListener : BeritaClickListenerRv? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.berita_rows, parent, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (link, title, description, date) = listBerita[position]

//        Glide.with(holder.itemView.context)
//            .load(photo) // URL Gambar
//            .into(holder.imgPhoto)

        holder.tvJudulBerita.text = title
        holder.tvDescriptionBerita.text = description
        holder.tvLinkBerita.text = link
        holder.tvDateBerita.text = date
        holder.itemView.setOnClickListener {
            clickListener?.onItemClickListenter(it, listBerita[position])
        }

    }

    override fun getItemCount(): Int = listBerita.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imgPhoto: ImageView = itemView.findViewById(R.id.)
        val tvJudulBerita: TextView = itemView.findViewById(R.id.tv_judul_berita)
        val tvDescriptionBerita: TextView = itemView.findViewById(R.id.tv_deskripsi_berita)
        val tvLinkBerita: TextView = itemView.findViewById(R.id.tv_link_berita)
        val tvDateBerita: TextView = itemView.findViewById(R.id.tv_date_berita)
    }
}