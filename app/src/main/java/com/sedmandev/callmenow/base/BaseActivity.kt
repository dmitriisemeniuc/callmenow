package com.sedmandev.callmenow.base

import android.app.Activity
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.sedmandev.callmenow.base.interfaces.BaseView
import com.sedmandev.callmenow.utils.NetworkHelper.ACTION_INTERNET_CONNECTION

/**
 * Base Activity of  all Activity classes.
 * It provides required methods, presenter instantiation and calls.
 *
 * @param P the type of the presenter the Activity is based on
 */
abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {

  private var internetConnectionIntent: IntentFilter = IntentFilter(ACTION_INTERNET_CONNECTION)

  protected lateinit var presenter: P

  protected abstract val contentViewId: Int

  protected abstract val statusBarColor: Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (contentViewId != -1) {
      setContentView(contentViewId)
    }
    presenter = instantiatePresenter()
  }

  override fun onStart() {
    super.onStart()
    if (statusBarColor != -1) {
      setStatusBarColor()
    }
  }

  /**
   * Instantiates the presenter the Activity is based on.
   */
  protected abstract fun instantiatePresenter(): P

  override fun getContext(): Context {
    return this
  }

  private fun setStatusBarColor(){
    setStatusBarColor(this, statusBarColor)
  }

  private fun setStatusBarColor(activity: Activity, @ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val window = activity.window

      // clear FLAG_TRANSLUCENT_STATUS flag:
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

      // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

      // finally change the color
      window.statusBarColor = ContextCompat.getColor(activity, color)
    }
  }
}
