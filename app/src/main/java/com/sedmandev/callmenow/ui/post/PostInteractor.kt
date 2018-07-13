package com.sedmandev.callmenow.ui.post

import com.sedmandev.callmenow.api.endpoints.UnAuthorizedApi
import com.sedmandev.callmenow.base.BaseInteractor
import com.sedmandev.callmenow.model.Post
import io.reactivex.Observable
import javax.inject.Inject

class PostInteractor(router: PostRouter) : BaseInteractor<PostRouter>(router) {

  override fun inject() {
    injector.inject(this)
  }

  @Inject
  lateinit var api: UnAuthorizedApi

  fun loadPosts(): Observable<List<Post>> {
    return api.getPosts()
  }
}
