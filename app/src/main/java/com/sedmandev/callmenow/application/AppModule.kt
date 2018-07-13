package com.sedmandev.callmenow.application

import android.content.Context
import com.google.gson.Gson
import com.sedmandev.callmenow.api.response.ErrorHttpReader
import com.sedmandev.callmenow.session.SessionData
import com.sedmandev.callmenow.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object AppModule {

  @Provides
  @Reusable
  @JvmStatic
  internal fun provideSessionData(context: Context, gson: Gson): SessionData {
    return SessionData(context, gson)
  }


  @Provides
  @Reusable
  @JvmStatic
  internal fun providesNetworkHelper(context: Context): NetworkHelper {
    return NetworkHelper(context)
  }

  @Provides
  @Reusable
  @JvmStatic
  internal fun providesErrorHttpReader(context: Context): ErrorHttpReader {
    return ErrorHttpReader(context.resources)
  }

  @Provides
  @Reusable
  @JvmStatic
  internal fun providesContext(): Context {
    return App.app
  }

  @Provides
  @Reusable
  @JvmStatic
  internal fun providesApp(): App {
    return App.app
  }
}
