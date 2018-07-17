package com.sedmandev.callmenow.base.interfaces

import android.content.Context

interface Router {

  fun navigateTo(context: Context, cls: Class<*>)
}