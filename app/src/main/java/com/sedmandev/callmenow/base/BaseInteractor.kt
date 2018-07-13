package com.sedmandev.callmenow.base

import com.sedmandev.callmenow.base.interfaces.Interactor
import com.sedmandev.callmenow.base.interfaces.Router
import com.sedmandev.callmenow.injection.component.DaggerInteractorInjector
import com.sedmandev.callmenow.injection.component.InteractorInjector
import com.sedmandev.callmenow.module.NetworkModule

abstract class BaseInteractor<out R: Router>(protected val router: Router) : Interactor {

  /**
   * Injects the required dependencies
   * */
  abstract fun inject()

  protected val injector : InteractorInjector = DaggerInteractorInjector
      .builder()
      .baseRouter(router)
      .networkModule(NetworkModule)
      .build()

  init {
    this.inject()
  }
}
