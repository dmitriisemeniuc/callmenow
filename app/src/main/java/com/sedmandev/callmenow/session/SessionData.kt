package com.sedmandev.callmenow.session

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import com.google.gson.Gson

class SessionData(context: Context, private val gson: Gson) {

  companion object {

    private const val KEY_ACCESS_TOKEN = "access_token"
  }

  private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

  var accessToken: String
    get() = getString(KEY_ACCESS_TOKEN)
    set(accessToken) = setString(KEY_ACCESS_TOKEN, accessToken)

  val useLogged: Boolean
    get() = !TextUtils.isEmpty(accessToken)

  private fun getString(key: String): String {
    return sharedPreferences.getString(key, "")
  }

  private fun getBoolean(key: String): Boolean {
    return sharedPreferences.getBoolean(key, java.lang.Boolean.FALSE)
  }

  private fun getInt(key: String): Int {
    return sharedPreferences.getInt(key, -1)
  }

  private fun getLong(key: String): Long {
    return sharedPreferences.getLong(key, -1L)
  }

  private fun setInt(key: String, value: Int) {
    val editor = sharedPreferences.edit()
    editor.putInt(key, value)
    editor.apply()
  }

  private fun setString(key: String, value: String) {
    val editor = sharedPreferences.edit()
    editor.putString(key, value)
    editor.apply()
  }

  private fun setBoolean(key: String, value: Boolean) {
    val editor = sharedPreferences.edit()
    editor.putBoolean(key, value)
    editor.apply()
  }

  private fun setLong(key: String, value: Long) {
    val editor = sharedPreferences.edit()
    editor.putLong(key, value)
    editor.apply()
  }

  fun cleanRepository() {
    val editor = sharedPreferences.edit()
    editor.clear()
    editor.apply()
  }
}
