package com.sedmandev.callmenow.ui.splash

import android.os.Bundle
import com.sedmandev.callmenow.R
import com.sedmandev.callmenow.base.BaseActivity

class SplashActivity : BaseActivity<SplashPresenter>(), SplashView {

  override val contentViewId: Int
    get() = R.layout.activity_splash

  override val statusBarColor: Int
    get() = android.R.color.black

  override fun instantiatePresenter(): SplashPresenter {
    return SplashPresenter(this, SplashInteractor(SplashRouter()))
  }

  /**
   * DataBinding instance
   */
  //private lateinit var binding: ActivitySplashBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //setContentView(R.layout.activity_splash)

    //binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

    presenter.onCreate()
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.onDestroy()
  }
}
