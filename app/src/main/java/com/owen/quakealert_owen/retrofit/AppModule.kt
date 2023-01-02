package com.owen.quakealert_owen.retrofit

import com.owen.quakealert_owen.helper.Const.BASE_URL_LOCALHOST
import com.owen.quakealert_owen.helper.Const.URL_GEMPA
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit):
            EndPointApi{
        return retrofit.create(EndPointApi::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCALHOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}