package com.sedmandev.callmenow.auth

import android.content.Context
import com.sedmandev.callmenow.session.SessionData
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AppTokenAuthenticator(private val sessionData: SessionData, private val context: Context) : Authenticator {

  override fun authenticate(route: Route?, response: Response?): Request? {
    sessionData.cleanRepository()
    // TODO: SplashActivity.start(context)
    return null
  }
}
