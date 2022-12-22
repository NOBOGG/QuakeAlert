package com.owen.quakealert_owen.retrofit

import com.owen.quakealert_owen.helper.Const
import com.owen.quakealert_owen.model.Data
import com.owen.quakealert_owen.model.Gempa
import com.owen.quakealert_owen.model.Infogempa
import retrofit2.Response
import retrofit2.http.GET


interface EndPointApi {
//    @GET("autogempa.json")
//    suspend fun getGempaTerbaru(
//    ):Response <Gempa>
    @GET("DataMKG/TEWS/autogempa.json")
    suspend fun getGempaData():Response<Data>
}