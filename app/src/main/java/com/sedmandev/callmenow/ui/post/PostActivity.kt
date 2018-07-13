package com.sedmandev.callmenow.ui.post

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.sedmandev.callmenow.R
import com.sedmandev.callmenow.api.messages.NetworkErrorMessages
import com.sedmandev.callmenow.base.BaseActivity
import com.sedmandev.callmenow.databinding.ActivityPostBinding
import com.sedmandev.callmenow.model.Post

/**
 * Activity displaying the list of posts
 */
class PostActivity /*: BaseActivity<PostPresenter>(), PostView {
    *//**
     * DataBinding instance
     *//*
    private lateinit var binding: ActivityPostBinding

    override fun instantiatePresenter(): PostPresenter {
        return PostPresenter(this, PostInteractor(PostRouter()))
    }

    *//**
     * The adapter for the list of posts
     *//*
    private val postsAdapter = PostAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)
        binding.adapter = postsAdapter
        binding.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun updatePosts(posts: List<Post>) {
        postsAdapter.updatePosts(posts)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }
}*/