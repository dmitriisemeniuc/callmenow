package com.sedmandev.callmenow.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class GoogleSignInHelper(private val googleSignInListener: GoogleSignInHelper.SignIn) {

  val TAG = GoogleSignInHelper::class.java.simpleName

  init {
    initFirebase()
  }

  lateinit var googleApiClient: GoogleApiClient
  lateinit var firebaseAuth: FirebaseAuth

  private fun initFirebase() {
    firebaseAuth = FirebaseAuth.getInstance()
  }

  fun initGoogleApiClient(activity: FragmentActivity, context: Context, requestClientId: String) {

    // Configure Google Sign In
    val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(requestClientId)
        .requestEmail()
        .build()

    // Creating and Configuring Google Api Client.
    googleApiClient = GoogleApiClient.Builder(context)
        .enableAutoManage(activity  /* OnConnectionFailedListener */) { }
        .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
        .build()
  }

  fun signInWithGoogle(activity: Activity) {
    val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
    activity.startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
  }

  fun onActivityResult(data: Intent, context: Context, activity: Activity){
    val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
    if (result.isSuccess) {
      // Google Sign In was successful, authenticate with Firebase
      firebaseAuthWithGoogle(result.signInAccount!!, firebaseAuth, activity)
    } else {
      Log.i(TAG, "Google SignIn result is success? ${result.isSuccess}.")
      Toast.makeText(context, "Some error occurred.", Toast.LENGTH_SHORT).show()
    }
  }

  private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount, firebaseAuth: FirebaseAuth?, activity: Activity) {
    Log.i(TAG, "Authenticating user with firebase.")
    val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
    firebaseAuth?.signInWithCredential(credential)?.addOnCompleteListener(activity) { task ->
      Log.i(TAG, "Firebase Authentication, is result a success? ${task.isSuccessful}.")
      if (task.isSuccessful) {
        // Sign in success, update UI with the signed-in user's information
        val user = firebaseAuth.currentUser
        googleSignInListener.onGoogleSignInSuccess(user!!.uid, user.displayName, user.email, user.photoUrl.toString())
      } else {
        // If sign in fails, display a message to the user.
        Log.e(TAG, "Authenticating with Google credentials in firebase FAILED !!")
      }
    }
  }

  interface SignIn {

    fun onGoogleSignInSuccess(uid: String, name: String?, email: String?, photoUrl: String)

    fun onGoogleSignInFailed()
  }
}
