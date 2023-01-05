package com.owen.quakealert_owen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owen.quakealert_owen.model.Comment
import com.owen.quakealert_owen.model.Users
import com.owen.quakealert_owen.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository):ViewModel() {

    //create comment
    fun createComment(comment: String, user_id: String) =
        repository.insertComment(comment, user_id)

    //create user
    fun createUser(name: String, username: String, password: String, email: String, status: String, image: String) =
        repository.insertUser(name, username, password, email, status, image)

    //update user
    fun updateUser(id:Int, name: String, username: String, password: String, email: String, status: String, image: String) =
        repository.updateUser(id, name, username, password, email, status, image)

    //login user
    fun loginUser(email: String,password: String) =
        repository.loginUser(email,password)

    //get user
    val _user : MutableLiveData<Users> by lazy {
        MutableLiveData<Users>()
    }

    val user : LiveData<Users>
        get() = _user

    fun getUserbyId(id:Int) = viewModelScope.launch {
        repository.getUserbyId(id).let {
            response ->
            if (response.isSuccessful){
                _user.postValue(response.body())
            }else{
                Log.e("Get User","Failed!")
            }
        }
    }


    //get comment
    val _comment : MutableLiveData<Comment> by lazy {
        MutableLiveData<Comment>()
    }

    val comment : LiveData<Comment>
        get() = _comment

    fun getComment() = viewModelScope.launch {
        repository.getComment().let {response ->
            if (response.isSuccessful){
                Log.e("Error comment data",response.body().toString())
                _comment.postValue(response.body())
            }else{
                Log.e("Get Comment","Failed!")
            }
        }
    }

    //delete comment
    fun deleteComment(id: Int) =
        repository.deleteComment(id)
}



