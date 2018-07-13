package com.sedmandev.callmenow.ui.post

import com.sedmandev.callmenow.R
import com.sedmandev.callmenow.api.PostApi
import com.sedmandev.callmenow.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * The Presenter that will present the Post view.
 * @param postView the Post view to be presented by the presenter
 * @property postApi the API interface implementation
 * @property subscription the subscription to the API call
 */
class PostPresenter(postView: PostView, private val interactor: PostInteractor) : BasePresenter<PostView>(postView) {

  /**
   * Inject this presenter to the PresenterInjector
   * */
  override fun inject() {
    injector.inject(this)
  }

  private var subscription: Disposable? = null

  override fun onCreate() {
    loadPosts()
  }

  override fun onResume() {}

  override fun onPause() {}

  override fun onStop() {}

  override fun onDestroy() {
    subscription?.dispose()
  }

  /**
   * Loads the posts from the API and presents them in the view when retrieved, or showss error if
   * any.
   */
  private fun loadPosts() {
    view.showLoading()
    subscription = interactor.loadPosts()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnTerminate { view.hideLoading() }
        .subscribe(
            { postList -> view.updatePosts(postList) },
            { view.showError(R.string.unknown_error) }
        )
  }
}