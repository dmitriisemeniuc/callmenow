package com.sedmandev.callmenow.ui.main

import android.content.Context
import com.sedmandev.callmenow.base.BaseInteractor
import com.sedmandev.callmenow.ui.splash.SplashActivity

class MainInteractor(router: MainRouter) : BaseInteractor<MainRouter>(router) {

  override fun inject() {
    injector.inject(this)
  }

  fun navigateToSplashScreen(context: Context) {
    router.startActivity(context, SplashActivity::class.java)
  }
}
