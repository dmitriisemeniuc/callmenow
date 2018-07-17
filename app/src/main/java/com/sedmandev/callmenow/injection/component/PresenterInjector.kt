package com.sedmandev.callmenow.injection.component

import com.sedmandev.callmenow.base.interfaces.BaseView
import com.sedmandev.callmenow.module.AppModule
import com.sedmandev.callmenow.ui.login.LoginPresenter
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
@Component(modules = [(AppModule::class)])
interface PresenterInjector {
  /**
   * Injects required dependencies into the specified presenter.
   * @param presenter presenter in which to inject the dependencies
   */
  fun inject(postPresenter: PostPresenter)
  fun inject(mainPresenter: MainPresenter)
  fun inject(splashPresenter: SplashPresenter)
  fun inject(loginPresenter: LoginPresenter)

  @Component.Builder
  interface Builder {
    fun build(): PresenterInjector

    fun appModule(appModule: AppModule): Builder

    @BindsInstance
    fun baseView(baseView: BaseView): Builder
  }
}
