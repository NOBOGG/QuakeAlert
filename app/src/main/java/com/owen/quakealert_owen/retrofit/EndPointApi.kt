package com.owen.quakealert_owen.retrofit

import com.owen.quakealert_owen.model.Data
import retrofit2.Response
import retrofit2.http.GET


interface EndPointApi {
//    @GET("autogempa.json")
//    suspend fun getGempaTerbaru(
//    ):Response <Gempa>
    @GET("DataMKG/TEWS/autogempa.json")
    suspend fun getGempaData():Response<Data>

    @GET("DataMKG/TEWS/gempadirasakan.json")
    suspend fun getGempaDirasakan():Response<Data>
}