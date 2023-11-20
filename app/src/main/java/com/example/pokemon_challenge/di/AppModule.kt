package com.example.pokemon_challenge.di

import com.example.pokemon_challenge.api.ApiService
import com.example.pokemon_challenge.api.ApiServiceCustom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = run {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    @Named("official")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    @Named("custom")
    fun provideRetrofitApiCustom(okHttpClientCustom: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.142.172:8080/pokemon/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClientCustom)
        .build()

    @Singleton
    @Provides
    fun provideApiService(@Named("official") retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideApiCustomService(@Named("custom") retrofitCustom: Retrofit): ApiServiceCustom = retrofitCustom.create(ApiServiceCustom::class.java)



}