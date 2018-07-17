package com.sedmandev.callmenow.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.View
import android.widget.Button
import com.sedmandev.callmenow.R
import com.sedmandev.callmenow.base.BaseActivity
import com.sedmandev.callmenow.utils.GoogleSignInHelper
import com.sedmandev.callmenow.utils.RC_GOOGLE_SIGN_IN

// http://www.appsdeveloperblog.com/firebase-social-authentication-example-kotlin/
class LoginActivity : BaseActivity<LoginPresenter>(), LoginView, View.OnClickListener {

  val TAG = LoginActivity::class.java.simpleName!!

  // Views
  lateinit var btnGoogleSignIn: Button

  /* ***********************************************************************************************
   * LIFECYCLE METHODS
   ************************************************************************************************/

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter.onCreate()
  }

  /* ***********************************************************************************************
   * OVERRIDE METHODS
   ************************************************************************************************/

  override val contentViewId: Int
    get() = R.layout.activity_login

  override val statusBarColor: Int
    get() = android.R.color.black

  override fun instantiatePresenter(): LoginPresenter {
    return LoginPresenter(this, LoginInteractor(LoginRouter()))
  }

  override fun getActivity(): Activity {
    return this
  }

  override fun getFragmentActivity(): FragmentActivity {
    return this
  }


  override fun initViews() {
    btnGoogleSignIn = findViewById<View>(R.id.btn_google_sign_in) as Button
    btnGoogleSignIn.setOnClickListener(this)
  }


  override fun onClick(view: View?) {
    when (view?.id) {
      R.id.btn_google_sign_in -> {
        presenter.signInWithGoogle()
      }
    }
  }

  public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == RC_GOOGLE_SIGN_IN) {
      presenter.onActivityResult(data)
    }
  }
}