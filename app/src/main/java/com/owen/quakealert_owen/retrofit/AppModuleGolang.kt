package com.owen.quakealert_owen.retrofit

import com.owen.quakealert_owen.helper.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleGolang {
    @Singleton
    @Provides
    @Named("golang")
    fun getRetrofitServiceInstance(retrofit: Retrofit):
            EndPointApiGolang{
        return retrofit.create(EndPointApiGolang::class.java)
    }

    @Singleton
    @Provides
    @Named("golang")
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL_LOCALHOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}