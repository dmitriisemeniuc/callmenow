package com.sedmandev.callmenow.ui.splash

import android.content.Context
import com.sedmandev.callmenow.base.BaseInteractor
import com.sedmandev.callmenow.ui.main.MainActivity

class SplashInteractor(router: SplashRouter) : BaseInteractor<SplashRouter>(router) {

  override fun inject() {
    injector.inject(this)
  }

  fun navigateToMainScreen(context: Context) {
    router.startActivity(context, MainActivity::class.java)
  }
}
