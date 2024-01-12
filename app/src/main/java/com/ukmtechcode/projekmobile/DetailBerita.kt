package com.ukmtechcode.projekmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ukmtechcode.projekmobile.R

class DetailBerita : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)

        // fungsi untuk menerima data intent yang dikirim
        val dataIntent = intent.getParcelableExtra<Berita>("testing")
        //inisiasi view
        val title = findViewById<TextView>(R.id.textJudulBerita)
        val description = findViewById<TextView>(R.id.textDeskripsiBerita)
        val linkberita = findViewById<TextView>(R.id.textLinkBerita)
        val tanggalberita = findViewById<TextView>(R.id.textDateBerita)

        //tampilkan gambar menggunakan glid
//        Glide.with(this)
//            .load(dataIntent?.photo)
//            .into(image)
        // tampilkan text untuk judul
        title.text = dataIntent?.title
        //tampilkan text untuk deskripsi
        description.text = dataIntent?.description
        //tampilkan text untuk link
        linkberita.text = dataIntent?.link
        //tampilkan text untuk pubdate
        tanggalberita.text = dataIntent?.pubDate
    }
}