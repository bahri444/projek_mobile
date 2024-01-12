package com.ukmtechcode.projekmobile

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BeritaHelper {
    private val baseUrl = "https://gismenara.lomboktengahkab.go.id/uploads/"
    fun getInstance(): Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}