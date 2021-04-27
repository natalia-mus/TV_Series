package com.example.tvseries.dagger.modules

import com.example.tvseries.API.APIService
import com.example.tvseries.objects.UrlObject
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): APIService {
        val retrofit = Retrofit.Builder()
            .baseUrl(UrlObject.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(APIService::class.java)
    }
}