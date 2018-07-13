package com.sedmandev.callmenow.injection.component

import com.sedmandev.callmenow.base.interfaces.BaseView
import com.sedmandev.callmenow.base.interfaces.Presenter
import com.sedmandev.callmenow.module.ContextModule
import com.sedmandev.callmenow.module.NetworkModule
import com.sedmandev.callmenow.ui.main.MainPresenter
import com.sedmandev.callmenow.ui.post.PostPresenter
import com.sedmandev.callmenow.ui.splash.SplashPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {
  /**
   * Injects required dependencies into the specified presenter.
   * @param presenter presenter in which to inject the dependencies
   */
  fun inject(presenter: PostPresenter)
  fun inject(presenter: MainPresenter)
  fun inject(presenter: SplashPresenter)

  @Component.Builder
  interface Builder {
    fun build(): PresenterInjector

    fun contextModule(contextModule: ContextModule): Builder

    @BindsInstance
    fun baseView(baseView: BaseView): Builder
  }
}
