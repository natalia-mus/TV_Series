package com.example.tvseries.dagger.modules

import com.example.tvseries.API.APIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    companion object {
        private const val BASE_URL = "https://www.episodate.com/api/"
    }

    @Singleton
    @Provides
    fun provideRetrofit(): APIService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(APIService::class.java)
    }
}