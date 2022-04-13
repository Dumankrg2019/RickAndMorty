package com.dev.repeatpaging01.network

import com.dev.repeatpaging01.network.response.RickAndMortyList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getDataOfCharacters(@Query("page") query: Int): RickAndMortyList
}