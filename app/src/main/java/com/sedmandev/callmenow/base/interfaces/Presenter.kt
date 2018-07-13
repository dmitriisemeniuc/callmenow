package com.sedmandev.callmenow.base.interfaces

interface Presenter {

  /**
   * This method may be called when the presenter view is created
   */
  fun onCreate()

  /**
   * This method may be called when the presenter view is resumed
   */
  fun onResume()

  /**
   * This method may be called when the presenter view is paused
   */
  fun onPause()

  /**
   * This method may be called when the presenter view is stopped
   */
  fun onStop()

  /**
   * This method may be called when the presenter view is destroyed
   */
  fun onDestroy()
}
