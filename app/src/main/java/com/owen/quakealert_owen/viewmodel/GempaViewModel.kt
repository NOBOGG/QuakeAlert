package com.owen.quakealert_owen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owen.quakealert_owen.model.Data
import com.owen.quakealert_owen.model.Gempa
import com.owen.quakealert_owen.model.Infogempa
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

//    init {
//        getGempaTerkini()
//    }

    fun getGempaTerkini() = viewModelScope.launch {
        repository.getGempa().let { response ->
            Log.e("EROORE", response.body().toString())
            if (response.isSuccessful) {
                var x : Data = response.body() as Data
                _gempaTerkini.postValue(x.Infogempa.gempa)

//                response.body().let {
//                    Log.e("EROORE", it.toString())
//                    //_gempaTerkini.postValue(it.)
//                }

//                Log.e("EROORE", response.message().toString())
//                _gempaTerkini.postValue(response.body()?.Infogempa!!.gempa as Gempa)
//                Log.e("test", _gempaTerkini.postValue(response.body()!!.Magnitude).toString()
            }else{
                Log.e("Get Gempa Data","Failed!")
            }
        }
    }
}