package com.example.onlinecinema.di

import com.example.onlinecinema.core.network.TypedResult
import com.example.onlinecinema.core.network.TypedResultAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreJsonModule {

    @Provides
    @Singleton
    fun provideGson(
        typedResultAdapter: TypedResultAdapter,
    ): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(TypedResult::class.java, typedResultAdapter)

        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideTypedResultAdapter() : TypedResultAdapter {
        return TypedResultAdapter()
    }
}