package com.sedmandev.callmenow.base

import com.sedmandev.callmenow.application.AppModule
import com.sedmandev.callmenow.base.interfaces.BaseView
import com.sedmandev.callmenow.base.interfaces.Presenter
import com.sedmandev.callmenow.injection.component.DaggerPresenterInjector
import com.sedmandev.callmenow.injection.component.PresenterInjector

/**
 * Parent presenter for all presenters.
 * It provides initial injections and required methods.
 *
 * @param V the type of the View the presenter is based on
 * @property view the view the presenter is based on
 * @property injector The injector used to inject required dependencies
 */
abstract class BasePresenter<out V : BaseView>(protected val view: V) : Presenter {

  abstract fun inject()

  protected val injector: PresenterInjector = DaggerPresenterInjector
      .builder()
      .baseView(view)
      .appModule(AppModule)
      .build()

  init {
    this.inject()
  }
}
