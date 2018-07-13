package com.sedmandev.callmenow.api.endpoints

import com.sedmandev.callmenow.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface UnAuthorizedApi {

  /**
   * Get the list of the pots from the API
   */
  @GET("/posts")
  fun getPosts(): Observable<List<Post>>
}
