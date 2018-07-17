package com.sedmandev.callmenow.ui.login

import android.app.Activity
import android.support.v4.app.FragmentActivity
import com.sedmandev.callmenow.base.interfaces.BaseView

interface LoginView : BaseView {

  fun getActivity(): Activity

  fun getFragmentActivity(): FragmentActivity
}