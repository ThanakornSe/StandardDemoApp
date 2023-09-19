package com.smartliving.standarddemoapp

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import scgwedotest.sdk.BuildConfig
import scgwedotest.sdk.api.SmartLivingApiInterface
import scgwedotest.sdk.api.SmartLivingApiRepository
import scgwedotest.sdk.api.interceptors.AuthenticatorInterceptor
import scgwedotest.sdk.api.repository.OneTrustRepository
import scgwedotest.sdk.api.repository.OneTrustRepositoryImpl
import scgwedotest.sdk.api.service.OneTrustService
import scgwedotest.sdk.home.instance.ScgUser
import scgwedotest.sdk.router.SmartLivingSDKRouter
import scgwedotest.sdk.router.SmartLivingSDKRouterImpl
import scgwedotest.sdk.storage.SharedPreferenceManagerSdk
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Singleton
    @Named("SCG_SDK_LOG")
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor { message ->
        Log.e("RESPONSE", message)
    }

    @Provides
    @Singleton
    @Named("SCG_SDK_CONVERTER")
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }


}
