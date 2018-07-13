package com.sedmandev.callmenow.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sedmandev.callmenow.BuildConfig
import com.sedmandev.callmenow.api.endpoints.AuthorizedApi
import com.sedmandev.callmenow.api.endpoints.UnAuthorizedApi
import com.sedmandev.callmenow.api.qualifiers.Authorized
import com.sedmandev.callmenow.api.qualifiers.UnAuthorized
import com.sedmandev.callmenow.auth.AppTokenAuthenticator
import com.sedmandev.callmenow.session.SessionData
import com.sedmandev.callmenow.utils.AddTokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {

  @Provides
  @Reusable
  @JvmStatic
  internal fun providesGson(): Gson {
    return GsonBuilder()
        .setLenient()
        .create()
  }

  @Provides
  @Reusable
  @JvmStatic
  internal fun providesUnAuthorizedApi(@UnAuthorized retrofit: Retrofit): UnAuthorizedApi {
    return retrofit.create<UnAuthorizedApi>(UnAuthorizedApi::class.java)
  }

  @Provides
  @Reusable
  @JvmStatic
  internal fun providesAuthorizedApi(@Authorized retrofit: Retrofit): AuthorizedApi {
    return retrofit.create<AuthorizedApi>(AuthorizedApi::class.java)
  }

  @Provides
  @Reusable
  @JvmStatic
  @Authorized
  internal fun providesAuthorizedRetrofit(retrofitBuilder: Retrofit.Builder,
                                          @Authorized okHttpClient: OkHttpClient): Retrofit {
    return retrofitBuilder
        .client(okHttpClient)
        .build()
  }

  @Provides
  @Reusable
  @JvmStatic
  @UnAuthorized
  internal fun providesUnAuthorizedRetrofit(retrofitBuilder: Retrofit.Builder,
                                            @UnAuthorized okHttpClient: OkHttpClient): Retrofit {
    return retrofitBuilder
        .client(okHttpClient)
        .build()
  }

  @Provides
  @Reusable
  @JvmStatic
  @Authorized
  internal fun providesAuthorizedOkHttp(authorizedOkHttpBuilder: OkHttpClient.Builder,
                                        authenticator: Authenticator,
                                        interceptor: AddTokenInterceptor): OkHttpClient {
    return authorizedOkHttpBuilder
        .authenticator(authenticator)
        .addInterceptor(interceptor)
        .build()
  }

  @Provides
  @Reusable
  @JvmStatic
  @UnAuthorized
  internal fun providesUnAuthorizedOkHttp(unAuthorizedOkHttp: OkHttpClient.Builder): OkHttpClient {
    return unAuthorizedOkHttp.build()
  }

  @Provides
  @Reusable
  @JvmStatic
  internal fun providesOkHttpBuilder(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {

    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
  }

  @Provides
  @Reusable
  @JvmStatic
  internal fun providesRetrofitBuilder(gson: Gson): Retrofit.Builder {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addCallAdapterFactory(
            com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(GsonConverterFactory.create(gson))
  }

  @Provides
  @Reusable
  @JvmStatic
  internal fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = if (BuildConfig.DEBUG)
      HttpLoggingInterceptor.Level.BODY
    else
      HttpLoggingInterceptor.Level.HEADERS
    return httpLoggingInterceptor
  }

  @Provides
  @Reusable
  @JvmStatic
  internal fun providesAuthenticator(sessionData: SessionData, context: Context): Authenticator {
    return AppTokenAuthenticator(sessionData, context)
  }

  @Provides
  @Reusable
  @JvmStatic
  internal fun providesAddTokenInterceptor(sessionData: SessionData): AddTokenInterceptor {
    return AddTokenInterceptor(sessionData)
  }
}
