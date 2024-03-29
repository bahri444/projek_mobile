package com.ukmtechcode.projekmobile

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Parcelize
data class Berita(
    @SerializedName("link")
    val link: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("pubDate")
    val pubDate: String,

    @SerializedName("thumbnail")
    val thumbnail: String,
):Parcelable