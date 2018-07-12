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
class PostPresenter(postView: PostView) : BasePresenter<PostView>(postView) {
    @Inject
    lateinit var postApi: PostApi

    private var subscription: Disposable? = null

    override fun onCreate() {
        loadPosts()
    }

    /**
     * Loads the posts from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    fun loadPosts() {
        view.showLoading()
        subscription = postApi
                .getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { postList -> view.updatePosts(postList) },
                        { view.showError(R.string.unknown_error) }
                )
    }

    override fun onDestroy() {
        subscription?.dispose()
    }
}