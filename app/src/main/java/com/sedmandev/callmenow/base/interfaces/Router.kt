package com.sedmandev.callmenow.base.interfaces

import android.content.Context

interface Router {

  fun startActivity(context: Context, cls: Class<*>)
}