package com.sedmandev.callmenow.ui.main

import android.os.Bundle
import com.sedmandev.callmenow.R
import com.sedmandev.callmenow.base.BaseActivity

class MainActivity : BaseActivity<MainPresenter>(), MainView {

  override fun instantiatePresenter(): MainPresenter {
    return MainPresenter(this, MainInteractor(MainRouter()))
  }

  override val contentViewId: Int
    get() = R.layout.activity_main

  override val statusBarColor: Int
    get() = android.R.color.black

  override fun onCreate(savedInstanceState : Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    //presenter.onCreate()
  }
}
