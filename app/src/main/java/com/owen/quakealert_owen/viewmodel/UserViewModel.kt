package com.owen.quakealert_owen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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






}



