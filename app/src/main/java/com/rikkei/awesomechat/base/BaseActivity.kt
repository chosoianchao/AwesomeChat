package com.rikkei.awesomechat.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rikkei.awesomechat.R

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    private var _viewBinding: V? = null
    protected val viewBinding: V
        get() = _viewBinding ?: throw Exception(getString(R.string.error_binding))

    @get:LayoutRes
    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        _viewBinding = DataBindingUtil.setContentView(this, layoutResource)
        viewBinding.lifecycleOwner = this
        initViews()
    }

    protected abstract fun initViews()

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}
