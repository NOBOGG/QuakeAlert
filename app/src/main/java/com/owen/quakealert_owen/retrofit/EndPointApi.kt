package com.owen.quakealert_owen.retrofit

import com.owen.quakealert_owen.model.Gempa
import retrofit2.Response
import retrofit2.http.GET

interface EndPointApi {
//    @GET("autogempa.json")
//    suspend fun getGempaTerbaru(
//    ):Response <Gempa>
    @GET("DataMKG/TEWS/autogempa.json")
    suspend fun  getGempaData():Response<Gempa>
}