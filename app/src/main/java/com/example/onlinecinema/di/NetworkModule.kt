package com.example.onlinecinema.di

import com.example.onlinecinema.core.network.QueryInterceptor
import com.example.onlinecinema.data.remote.MovieApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMovieApi(
        gson: Gson,
    ): MovieApi {

        val client = OkHttpClient.Builder()
            .addInterceptor(QueryInterceptor(hashMapOf("appid" to "qwerty"))) //TODO: replace with api key
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("http://16.16.74.104:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MovieApi::class.java)
    }

}