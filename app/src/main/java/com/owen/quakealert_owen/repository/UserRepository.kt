package com.owen.quakealert_owen.repository

import com.owen.quakealert_owen.retrofit.EndPointApi
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: EndPointApi) {
    fun insertComment(comment: String, user_id: String)
    = api.insertComment(comment, user_id)

    //create user
    fun insertUser(name: String, username: String, password: String, email: String, status: String, image: String)
    = api.insertUser(name, username, password, email, status, image)

    //login user
    fun loginUser(email: String,password: String) =
        api.loginUser(email,password)

    //get comment
    suspend fun getComment() = api.getComment()

    //get user
    suspend fun getUserbyId(id:Int) = api.getUserbyId(id)

    //update user
    fun updateUser(id:Int, name: String, username: String, password: String, email: String, status: String, image: String)
    = api.updateUser(id, name, username, password, email, status, image)

    //delete comment
    fun deleteComment(id:Int)
        = api.deleteComment(id)
}
