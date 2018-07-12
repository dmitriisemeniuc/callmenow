package com.sedmandev.callmenow.base

import com.sedmandev.callmenow.injection.component.DaggerPresenterInjector
import com.sedmandev.callmenow.injection.component.PresenterInjector
import com.sedmandev.callmenow.module.ContextModule
import com.sedmandev.callmenow.module.NetworkModule
import com.sedmandev.callmenow.ui.post.PostPresenter

/**
 * Parent presenter for all presenters.
 * It provides initial injections and required methods.
 *
 * @param V the type of the View the presenter is based on
 * @property view the view the presenter is based on
 * @property injector The injector used to inject required dependencies
 */
abstract class BasePresenter<out V : BaseView>(protected val view: V) {
  private val injector: PresenterInjector = DaggerPresenterInjector
      .builder()
      .baseView(view)
      .contextModule(ContextModule)
      .networkModule(NetworkModule)
      .build()

  init {
    inject()
  }

  /**
   * This method may be called when the presenter view is created
   */
  open fun onCreate(){}

  /**
   * This method may be called when the presenter view is destroyed
   */
  open fun onDestroy(){}

  /**
   * Injects the required dependencies
   */
  private fun inject() {
    when (this) {
      is PostPresenter -> injector.inject(this)
    }
  }
}
