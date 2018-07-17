package com.sedmandev.callmenow.ui.splash

import com.sedmandev.callmenow.base.BaseInteractor

class SplashInteractor(router: SplashRouter) : BaseInteractor<SplashRouter>(router) {

  override fun inject() {
    injector.inject(this)
  }
}
