package com.sedmandev.callmenow.ui.login

import com.sedmandev.callmenow.base.BaseInteractor

class LoginInteractor(router: LoginRouter): BaseInteractor<LoginRouter>(router) {

  override fun inject() {
    injector.inject(this)
  }
}
