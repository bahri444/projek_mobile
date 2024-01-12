package com.ukmtechcode.projekmobile

import retrofit2.Response
import retrofit2.http.GET

interface BeritaApi {
    @GET("berita.json")
    suspend fun getBerita(): Response<List<Berita>>
}