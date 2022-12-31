package com.owen.quakealert_owen.repository

import com.owen.quakealert_owen.retrofit.EndPointApi
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: EndPointApi) {
    suspend fun insertComment(comment: String, user_id: Int)
    = api.insertComment(comment, user_id)

    fun insertUser(name: String, username: String, password: String, email: String, status: String, image: String)
    = api.insertUser(name, username, password, email, status, image)
}
