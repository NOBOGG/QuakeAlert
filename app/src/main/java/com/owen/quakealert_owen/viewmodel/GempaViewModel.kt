package com.owen.quakealert_owen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owen.quakealert_owen.model.*
import com.owen.quakealert_owen.repository.GempaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GempaViewModel @Inject constructor(private val repository: GempaRepository):ViewModel() {

    val _gempaTerkini : MutableLiveData<Infogempa> by lazy{
        MutableLiveData<Infogempa>()
    }

    val gempaTerkini: LiveData<Infogempa>
        get() = _gempaTerkini

    fun getGempaTerkini() = viewModelScope.launch {
        repository.getGempaDirasakan().let { response ->
            Log.e("EROORE", response.body().toString())
            if (response.isSuccessful) {
                var x : Data = response.body() as Data
                _gempaTerkini.postValue(x.Infogempa)
            }else{
                Log.e("Get Gempa Data","Failed!")
            }
        }
    }

    val _gempaDirasakan : MutableLiveData<Infogempa> by lazy{
        MutableLiveData<Infogempa>()
    }

    val gempaDirasakan: LiveData<Infogempa>
        get() = _gempaDirasakan

    fun getGempaHistory() = viewModelScope.launch {
        repository.getGempaDirasakan().let { response ->
            Log.e("EROOREhis", response.body().toString())
            if (response.isSuccessful) {
                var x : Data = response.body() as Data
                _gempaDirasakan.postValue(x.Infogempa)
            }else{
                Log.e("Get Gempa Data","Failed!")
            }
        }
    }

}
