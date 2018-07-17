package com.sedmandev.callmenow.base.interfaces

import android.content.Context

interface Interactor {

  fun navigateTo(context: Context, cls: Class<*>)
}
