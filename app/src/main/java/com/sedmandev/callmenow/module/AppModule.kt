package com.sedmandev.callmenow.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sedmandev.callmenow.base.interfaces.BaseView
import com.sedmandev.callmenow.session.SessionData
import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 * Module which provides all required dependencies about Context
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object AppModule {
  /**
   * Provides the Context
   * @param baseView the BaseView used to provides the context
   * @return the Context to be provided
   */
  @Provides
  @JvmStatic
  internal fun provideContext(baseView: BaseView): Context {
    return baseView.getContext()
  }

  /**
   * Provides the Application Context
   * @param context Context in which the application is running
   * @return the Application Context to be provided
   */
  @Provides
  @JvmStatic
  internal fun provideApplication(context: Context): Application {
    return context.applicationContext as Application
  }

  /**
   * Provides the SessionData
   * @param context Context in which the application is running
   * @param gson Gson provided by Gson builder
   * @return the SessionData to be provided
   */
  @Provides
  @Reusable
  @JvmStatic
  internal fun provideSessionData(context: Context, gson: Gson): SessionData {
    return SessionData(context, gson)
  }

  /**
   * Provides the Gson
   * @return the Gson to be provided
   */
  @Provides
  @Reusable
  @JvmStatic
  internal fun providesGson(): Gson {
    return GsonBuilder()
        .setLenient()
        .create()
  }
}
