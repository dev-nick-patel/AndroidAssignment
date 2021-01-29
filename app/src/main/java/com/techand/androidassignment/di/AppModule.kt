package com.techand.androidassignment.di

import android.content.Context
import com.techand.androidassignment.data.local.AppDatabase
import com.techand.androidassignment.data.local.RowDao
import com.techand.androidassignment.data.remote.ApiService
import com.techand.androidassignment.data.remote.MainRemoteDataSource
import com.techand.androidassignment.data.repository.MainRepository
import com.techand.androidassignment.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApi(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)


    @Singleton
    @Provides
    fun provideRowDao(db: AppDatabase) = db.rowsDao()


    @Singleton
    @Provides
    fun provideMainRemoteDataSource(apiService: ApiService) =
        MainRemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: MainRemoteDataSource,
        localDataSource: RowDao
    ) =
        MainRepository(remoteDataSource, localDataSource)

}