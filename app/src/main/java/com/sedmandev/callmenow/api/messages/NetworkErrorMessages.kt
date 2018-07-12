package com.sedmandev.callmenow.api.messages

enum class NetworkErrorMessages {

  NO_INTERNET_CONNECTION { override fun msg() = "Please check your internet connection." },
  SERVER_PROBLEM { override fun msg() = "Please try again later." },
  NOT_FOUND { override fun msg() = "Requested data not found." },
  NOT_AUTHORIZED { override fun msg() = "User not found. Please verify your credentials." },
  NO_SERVER_CONNECTION { override fun msg() = "The connection to the server was lost. \nPlease check you internet connection or try later." },
  ERROR_IN_DATA { override fun msg() = "There was an error in data." };

  abstract fun msg(): String
}