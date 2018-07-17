package com.sedmandev.callmenow.ui.login

import android.content.Intent
import android.util.Log
import com.sedmandev.callmenow.R
import com.sedmandev.callmenow.base.BasePresenter
import com.sedmandev.callmenow.utils.GoogleSignInHelper

class LoginPresenter(loginView: LoginView, private val interactor: LoginInteractor) :

    BasePresenter<LoginView>(loginView), GoogleSignInHelper.SignIn {

  val TAG = LoginPresenter::class.java.simpleName!!

  lateinit var googleSignInHelper: GoogleSignInHelper

  override fun inject() {
    injector.inject(this)
  }

  override fun onCreate() {
    view.initViews()
    initAuth()
  }

  override fun onResume() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onPause() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onStop() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onDestroy() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  private fun initAuth() {
    googleSignInHelper = GoogleSignInHelper(this)
    googleSignInHelper.initGoogleApiClient(view.getFragmentActivity(), view.getContext(),
        view.getContext().getString(R.string.request_client_id))
  }

  fun onActivityResult(data: Intent) {
    googleSignInHelper.onActivityResult(data, view.getContext(), view.getActivity())
  }

  fun signInWithGoogle(){
    googleSignInHelper.signInWithGoogle(view.getActivity())
  }

  override fun onGoogleSignInSuccess(uid: String, name: String?, email: String?, photoUrl: String) {
    Log.i(TAG, "Google sign in successfully, userName: $name.")
  }

  override fun onGoogleSignInFailed() {
    Log.i(TAG, "Google sign in failed")
  }
}
