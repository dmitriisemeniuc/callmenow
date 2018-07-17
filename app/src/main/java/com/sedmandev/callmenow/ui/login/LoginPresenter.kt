package com.sedmandev.callmenow.ui.login

import com.sedmandev.callmenow.base.BasePresenter

class LoginPresenter(loginView: LoginView, private val interactor: LoginInteractor) :

    BasePresenter<LoginView>(loginView) {

  override fun inject() {
    injector.inject(this)
  }

  override fun onCreate() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
