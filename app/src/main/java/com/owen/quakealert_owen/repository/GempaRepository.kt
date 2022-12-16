package com.owen.quakealert_owen.repository

import com.owen.quakealert_owen.retrofit.EndPointApi
import javax.inject.Inject

class GempaRepository @Inject constructor(private val api: EndPointApi) {
    suspend fun getGempa() = api.getGempaData()
}