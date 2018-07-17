package com.sedmandev.callmenow.ui.login

import com.sedmandev.callmenow.R
import com.sedmandev.callmenow.base.BaseActivity

class LoginActivity : BaseActivity<LoginPresenter>(), LoginView {

  override val contentViewId: Int
    get() = R.layout.activity_login

  override val statusBarColor: Int
    get() = android.R.color.black

  override fun instantiatePresenter(): LoginPresenter {
    return LoginPresenter(this, LoginInteractor(LoginRouter()))
  }
}