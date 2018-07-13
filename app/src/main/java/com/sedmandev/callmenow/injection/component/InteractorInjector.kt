package com.sedmandev.callmenow.injection.component

import com.sedmandev.callmenow.base.interfaces.Router
import com.sedmandev.callmenow.module.NetworkModule
import com.sedmandev.callmenow.ui.post.PostInteractor
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for interactors.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface InteractorInjector {
  /**
   * Injects required dependencies into the specified interactor.
   * @param interactor interactor in which to inject the dependencies
   */
  fun inject(interactor: PostInteractor)

  @Component.Builder
  interface Builder {
    fun build(): InteractorInjector

    fun networkModule(networkModule: NetworkModule): Builder

    @BindsInstance
    fun baseRouter(baseRouter : Router) : Builder
  }
}