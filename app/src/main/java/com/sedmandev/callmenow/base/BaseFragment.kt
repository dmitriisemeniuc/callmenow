package com.sedmandev.callmenow.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedmandev.callmenow.base.interfaces.BaseView

abstract class BaseFragment<P : BasePresenter<BaseView>> : Fragment(), BaseView  {

  protected lateinit var presenter: P

  protected abstract val layoutId: Int

  protected val baseActivity: BaseActivity<*>
    get() = (activity as BaseActivity<*>?)!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(layoutId, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.onCreate()
  }
}
