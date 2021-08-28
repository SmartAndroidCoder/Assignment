package com.shaadi.assignment.di.module

import android.app.Application
import com.shaadi.assignment.BuildConfig
import com.shaadi.assignment.data.api.API
import com.shaadi.assignment.data.repository.MainRepository
import com.shaadi.assignment.data.repository.MainRepositoryImp
import com.shaadi.assignment.data.room.AppDatabase
import com.shaadi.assignment.data.room.dao.UsersResponseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): API = retrofit.create(API::class.java)


    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Provides
    fun provideUsersResponseDao(db: AppDatabase): UsersResponseDao = db.getUsersResponseDao()

    @Provides
    fun provideMyRepository(usersResponseDao: UsersResponseDao, api: API): MainRepository =
        MainRepositoryImp(usersResponseDao, api)
}