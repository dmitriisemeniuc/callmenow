package com.sedmandev.callmenow.injection.component

import com.sedmandev.callmenow.base.interfaces.Router
import com.sedmandev.callmenow.module.AppModule
import com.sedmandev.callmenow.module.NetworkModule
import com.sedmandev.callmenow.ui.login.LoginInteractor
import com.sedmandev.callmenow.ui.main.MainInteractor
import com.sedmandev.callmenow.ui.post.PostInteractor
import com.sedmandev.callmenow.ui.splash.SplashInteractor
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for interactors.
 */
@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class)])
interface InteractorInjector {
  /**
   * Injects required dependencies into the specified interactor.
   * @param interactor in which to inject the dependencies
   */
  fun inject(postInteractor: PostInteractor)
  fun inject(loginInteractor: LoginInteractor)
  fun inject(splashInteractor: SplashInteractor)
  fun inject(mainInteractor: MainInteractor)

  @Component.Builder
  interface Builder {
    fun build(): InteractorInjector

    fun appModule(appModule: AppModule): Builder
    fun networkModule(networkModule: NetworkModule): Builder

    @BindsInstance
    fun router(router : Router) : Builder
  }
}