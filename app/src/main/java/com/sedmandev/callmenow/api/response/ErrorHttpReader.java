package com.sedmandev.callmenow.api.response;

import android.content.res.Resources;
import android.util.JsonReader;
import android.util.Log;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.UnknownHostException;

public class ErrorHttpReader {

  private final WeakReference<Resources> resources;

  private boolean readMessage = false;

  public ErrorHttpReader() {
    this.resources = new WeakReference<>(null);
  }

  public ErrorHttpReader(Resources resources) {
    this.resources = new WeakReference<>(resources);
  }

  public String getMessage(Throwable rawMessage) {
    if(resources.get() == null){
      return null;
    }
    readMessage = false;
    if (rawMessage instanceof ConnectException
        || rawMessage instanceof UnknownHostException) {
      // TODO: return resources.get().getString(R.string.no_server_connection);
      //return NetworkErrorMessage.NO_SERVER_CONNECTION;
    }
    if (rawMessage instanceof NullPointerException ||
        rawMessage instanceof IllegalStateException ||
        rawMessage instanceof MalformedJsonException) {
      // TODO: return resources.get().getString(R.string.error_in_data);
      //return NetworkErrorMessage.ERROR_IN_DATA;
    }
    if (rawMessage instanceof HttpException) {
      if (((HttpException) rawMessage).code() == 401
          || ((HttpException) rawMessage).code() == 403) {
        // TODO: return resources.get().getString(R.string.not_authorized);
        //return NetworkErrorMessage.NOT_AUTHORIZED;
      }
      if (((HttpException) rawMessage).code() == 404) {
        // TODO: return resources.get().getString(R.string.not_found);
        //return NetworkErrorMessage.NOT_FOUND;
      }
      if (((HttpException) rawMessage).code() == 500) {
        // TODO: return resources.get().getString(R.string.server_problem);
        //return NetworkErrorMessage.SERVER_PROBLEM;
      }
    }

    if(rawMessage instanceof java.net.SocketException
        || rawMessage instanceof java.net.SocketTimeoutException){
      // TODO: return resources.get().getString(R.string.server_problem);
      //return NetworkErrorMessage.SERVER_PROBLEM;
    }

    if(rawMessage instanceof java.io.EOFException){
      // TODO: return resources.get().getString(R.string.server_problem);
      //return NetworkErrorMessage.SERVER_PROBLEM;
    }

    if(rawMessage instanceof com.google.gson.JsonSyntaxException){
      // TODO: return resources.get().getString(R.string.error_on_parsing_data);
      //return NetworkErrorMessage.ERROR_ON_PARSING_DATA;
    }

    try {
      JsonReader reader = new JsonReader(
          new InputStreamReader(((HttpException) rawMessage).response().errorBody().byteStream()));

      reader.beginObject();

      while (reader.hasNext()) {
        android.util.JsonToken nextToken = reader.peek();
        if (JsonToken.BEGIN_OBJECT.toString().equals(nextToken.toString())) {
          reader.beginObject();
        } else if (JsonToken.NAME.toString().equals(nextToken.toString())) {

          String name1 = reader.nextName();
          String TOKEN_ERROR_KEY = "error";
          if (name1.equalsIgnoreCase(TOKEN_ERROR_KEY)) {
            reader.beginObject();
            while (reader.hasNext()) {
              android.util.JsonToken nextError = reader.peek();
              if (nextError.toString().equalsIgnoreCase(JsonToken.NAME.toString())) {
                Log.d("key", reader.nextName());
              } else if (nextError.toString().equalsIgnoreCase(JsonToken.STRING.toString())) {
                String message = reader.nextString();
                if (readMessage) {
                  return message;
                }
              } else if (nextError.toString().equalsIgnoreCase(JsonToken.BOOLEAN.toString())) {
                Log.d("value", Boolean.toString(reader.nextBoolean()));
              } else if (nextError.toString().equalsIgnoreCase(JsonToken.BEGIN_OBJECT.toString())) {
                readMessage = true;
                reader.beginObject();
              } else if (nextError.toString().equalsIgnoreCase(JsonToken.END_OBJECT.toString())) {
                reader.endObject();
              }
            }
            reader.endObject();
          }
        } else if (JsonToken.STRING.toString().equals(nextToken.toString())) {
          reader.nextString();
        } else if (JsonToken.NUMBER.toString().equals(nextToken.toString())) {
          reader.nextLong();
        }
        break;
      }
      reader.close();
    } catch (IOException | ClassCastException e) {
      e.printStackTrace();
      return "Error getting data from the server";
    }
    return "";
  }
}
