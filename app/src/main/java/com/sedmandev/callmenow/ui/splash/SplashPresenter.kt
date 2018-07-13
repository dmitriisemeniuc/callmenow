package com.sedmandev.callmenow.ui.splash

import com.sedmandev.callmenow.base.BasePresenter
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class SplashPresenter(splashView: SplashView, private val interactor: SplashInteractor) :
    BasePresenter<SplashView>(splashView) {

  /**
   * Inject this presenter to the PresenterInjector
   * */
  override fun inject() {
    injector.inject(this)
  }

  override fun onCreate() {
    Completable.complete()
        .delay(3, TimeUnit.SECONDS)
        .doOnComplete({ interactor.navigateToMainScreen(view.getContext()) })
        .subscribe()
  }

  override fun onResume() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onPause() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onStop() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onDestroy() {
    subscription?.dispose()
  }

  private var subscription: Disposable? = null
}
