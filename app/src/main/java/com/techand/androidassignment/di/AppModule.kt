package com.techand.androidassignment.di

import com.techand.androidassignment.data.remote.ApiService
import com.techand.androidassignment.data.repository.DefaultMainRepository
import com.techand.androidassignment.data.repository.MainRepository
import com.techand.androidassignment.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.annotation.Signed
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDefaultMainRepository(
        apiService: ApiService
    )= DefaultMainRepository(apiService) as MainRepository

    @Singleton
    @Provides
    fun provideApi(): ApiService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)
    }
}