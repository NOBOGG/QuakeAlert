package com.owen.quakealert_owen.repository

import com.owen.quakealert_owen.retrofit.EndPointApi
import javax.inject.Inject

class GempaRepository @Inject constructor(private val api: EndPointApi) {
    suspend fun getGempa() = api.getGempaData()

    suspend fun getGempaDirasakan() = api.getGempaDirasakan()

    //get gempahis
    suspend fun getGempaHistory() = api.getGempaHistory()

    //insert gempa
    fun insertGempa(wilayah: String, tanggal: String, magnitudo: String) = api.insertGempa(wilayah, tanggal, magnitudo)
}