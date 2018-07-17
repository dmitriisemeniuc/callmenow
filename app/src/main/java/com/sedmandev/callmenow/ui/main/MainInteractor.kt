package com.sedmandev.callmenow.ui.main

import com.sedmandev.callmenow.base.BaseInteractor

class MainInteractor(router: MainRouter) : BaseInteractor<MainRouter>(router) {

  override fun inject() {
    injector.inject(this)
  }
}
