package com.sedmandev.callmenow.utils

import com.sedmandev.callmenow.session.SessionData
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Used to add access token to every request for authenticated user
 */
class AddTokenInterceptor(private val sessionData: SessionData) : Interceptor {

  companion object {

    private const val AUTH_HEADER = "Authorization"
    private const val BEARER = "Bearer "
  }

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()
    val requestBuilder = original.newBuilder()
        .addHeader(AUTH_HEADER, BEARER + sessionData.accessToken)
    return chain.proceed(requestBuilder.build())
  }
}
