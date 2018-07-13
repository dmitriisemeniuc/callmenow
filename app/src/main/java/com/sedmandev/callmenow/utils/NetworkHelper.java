package com.sedmandev.callmenow.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Generic reusable network methods.
 */
public class NetworkHelper {

  private static final int TYPE_WIFI          = 1;
  private static final int TYPE_MOBILE        = 2;
  private static final int TYPE_NOT_CONNECTED = 0;

  public static final int
      NETWORK_STATUS_NOT_CONNECTED  = 0,
      NETWORK_STATUS_WIFI           = 1,
      NETWORK_STATUS_MOBILE         = 2;

  public static final String ACTION_INTERNET_CONNECTION  = "android.net.conn.CONNECTIVITY_CHANGE";

  private Context context;

  public NetworkHelper(Context context) {
    this.context = context;
  }

  /**
   * @return true if connected, false otherwise.
   */
  public boolean isOnline() {
    ConnectivityManager connMgr = (ConnectivityManager)
        this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    return (connMgr != null && connMgr.getActiveNetworkInfo() != null && connMgr.getActiveNetworkInfo().isConnected());
  }

  private static int getConnection(Context context) {
    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    if(cm != null){
      NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
      if (null != activeNetwork) {
        if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
          return TYPE_WIFI;

        if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
          return TYPE_MOBILE;
      }
    }
    return TYPE_NOT_CONNECTED;
  }

  public static int getConnectionStatus(Context context) {
    int conn = NetworkHelper.getConnection(context);
    int status = 0;
    if (conn == NetworkHelper.TYPE_WIFI) {
      status = NETWORK_STATUS_WIFI;
    } else if (conn == NetworkHelper.TYPE_MOBILE) {
      status =NETWORK_STATUS_MOBILE;
    } else if (conn == NetworkHelper.TYPE_NOT_CONNECTED) {
      status = NETWORK_STATUS_NOT_CONNECTED;
    }
    return status;
  }
}
