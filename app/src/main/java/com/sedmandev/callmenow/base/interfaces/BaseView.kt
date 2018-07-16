package com.sedmandev.callmenow.base.interfaces

import android.content.Context

/**
 * Parent view view for all views.
 */
interface BaseView {
  /**
   * Returns the Context in which the application is running.
   * @return the Context in which the application is running
   */
  fun getContext(): Context
}
