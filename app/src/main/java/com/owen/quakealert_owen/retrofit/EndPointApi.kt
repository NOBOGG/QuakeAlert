package com.owen.quakealert_owen.retrofit

import com.google.gson.JsonObject
import com.owen.quakealert_owen.model.Gempa
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointApi {
    @GET("autogempa.json")
    suspend fun getGempaTerbaru(
//        @Query("Dirasakan") Dirasakan:String,
//        @Query("Wilayah") Wilayah:String,
//        @Query("Tanggal") Tanggal:String,
    ):Response <Gempa>
}