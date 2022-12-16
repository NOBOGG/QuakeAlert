package com.owen.quakealert_owen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owen.quakealert_owen.model.Gempa
import com.owen.quakealert_owen.repository.GempaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GempaViewModel @Inject constructor(private val repository: GempaRepository):ViewModel() {
    val _gempaTerkini : MutableLiveData<Gempa> by lazy{
        MutableLiveData<Gempa>()
    }
//    val _gempaTerkini = MutableLiveData<Gempa>()

    val gempaTerkini: LiveData<Gempa>
        get() = _gempaTerkini

    init {
        getGempaTerkini()
    }

    private fun getGempaTerkini() = viewModelScope.launch {
        repository.getGempa().let { response ->
            if (response.isSuccessful){
                _gempaTerkini.postValue(response.body() as Gempa)
//                Log.e("test", _gempaTerkini.postValue(response.body()!!.Magnitude).toString()
            }else{
                Log.e("Get Data","Failed!")
            }
        }
    }
}